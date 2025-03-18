package com.nandaiqbalh.muppi.app

import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenRoot
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenRoot
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
	MaterialTheme {

		val navController = rememberNavController()

		NavHost(
			navController = navController,
			startDestination = Route.OnboardingGraph
		) {

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
								popUpTo(Route.OnboardingGraph) {
									inclusive = true
								}
							}
						}
					)
				}
			}

			navigation<Route.HomeGraph>(
				startDestination = Route.HomeScreen
			) {

				composable<Route.HomeScreen> {
					val viewModel = koinViewModel<HomeScreenViewModel>()

					HomeScreenRoot(
						viewModel = viewModel,
						onClickItem = { id ->

						},
						onClickSeeAll = { source ->

						}
					)
				}
			}
		}
	}
}