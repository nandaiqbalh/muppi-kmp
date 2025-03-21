package com.nandaiqbalh.muppi.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nandaiqbalh.muppi.core.utils.orZero
import com.nandaiqbalh.muppi.explore_feature.presentation.ExploreScreenRoot
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.DetailMovieScreenRoot
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.DetailMovieViewModel
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenRoot
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenRoot
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import com.nandaiqbalh.muppi.saved_feature.presentation.SavedScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
	MaterialTheme {
		val navController = rememberNavController()

		Scaffold(
			bottomBar = {
				AnimatedVisibility(
					visible = isBottomBarVisible(navController),
					enter = fadeIn(),
					exit = fadeOut(animationSpec = tween(durationMillis = 0))
				) {
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
							onClickItem = { id ->
								// Pass the movieId to the DetailMovieScreen via the navigation route
								navController.navigate(Route.DetailMovieScreen(id))
							},
							onClickSeeAll = { source -> }
						)
					}

					composable<Route.DetailMovieScreen> {
						val viewModel = koinViewModel<DetailMovieViewModel>()

						// Get the movieId from the nav arguments
						val movieId = it.arguments?.getInt("movieId")?.orZero()

						DetailMovieScreenRoot(
							viewModel = viewModel,
							movieId = movieId.orZero(),
							onClickBack = {
								navController.popBackStack()
							},
							onClickCast = { id -> }
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

	return when (currentRoute) {
		Route.HomeScreen.toString(),
		Route.ExploreScreen.toString(),
		Route.SavedScreen.toString() -> true
		else -> false
	}
}
