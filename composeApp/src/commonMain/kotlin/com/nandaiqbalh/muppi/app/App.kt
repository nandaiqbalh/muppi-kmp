package com.nandaiqbalh.muppi.app

import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nandaiqbalh.muppi.explore_feature.presentation.ExploreScreenRoot
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenRoot
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenRoot
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import com.nandaiqbalh.muppi.saved_feature.presentation.SavedScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
	MaterialTheme {
		val navController = rememberNavController()

		Scaffold(
			bottomBar = {
				if (isBottomBarVisible(navController)) {
					BottomNavigationBar(navController)
				}
			}
		) {
			NavHost(
				navController = navController,
				startDestination = Route.OnboardingGraph,
				modifier = Modifier
			) {
				// Onboarding Flow
				navigation<Route.OnboardingGraph>(
					startDestination = Route.SplashScreen,
					exitTransition = { fadeOut() }
				) {
					composable<Route.SplashScreen> {
						val viewModel = koinViewModel<SplashScreenViewModel>()
						SplashScreenRoot(
							viewModel = viewModel,
							onCompleteSplash = {
								navController.navigate(Route.HomeGraph) {
									popUpTo(Route.OnboardingGraph) { inclusive = true }
								}
							}
						)
					}
				}

				// Home Flow with Bottom Navigation
				navigation<Route.HomeGraph>(
					startDestination = Route.HomeScreen
				) {
					composable<Route.HomeScreen> {
						val viewModel = koinViewModel<HomeScreenViewModel>()
						HomeScreenRoot(
							viewModel = viewModel,
							onClickItem = { id -> },
							onClickSeeAll = { source -> }
						)
					}
				}

				// explore
				navigation<Route.ExploreGraph>(
					startDestination = Route.ExploreScreen
				) {
					composable<Route.ExploreScreen> {
						ExploreScreenRoot(

						)
					}
				}

				// saved
				navigation<Route.SavedGraph>(
					startDestination = Route.SavedScreen
				) {
					composable<Route.SavedScreen> {
						SavedScreen(

						)
					}
				}
			}
		}
	}
}

/**
 * Determines if the bottom navigation bar should be visible based on the current route.
 */
@Composable
fun isBottomBarVisible(navController: NavController): Boolean {
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route?.substringAfterLast(".")

	// State to track visibility after a delay
	var isVisible by remember { mutableStateOf(false) }

	// Use LaunchedEffect to wait for 3 seconds before showing the Bottom Bar
	LaunchedEffect(currentRoute) {
		// Delay for 3 seconds
		delay(2000)
		// After 3 seconds, make the Bottom Navigation Bar visible
		isVisible = currentRoute in listOf(
			Route.HomeScreen.toString(),
			Route.ExploreScreen.toString(),
			Route.SavedScreen.toString()
		)
	}

	// Return the visibility state after 3 seconds
	return isVisible
}