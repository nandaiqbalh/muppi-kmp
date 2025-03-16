package com.nandaiqbalh.muppi.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenRoot
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
			startDestination = Route.SplashScreen
		) {

			composable<Route.SplashScreen>(
				exitTransition = {
					fadeOut(
						tween(1000)
					)
				}
			) {
				val viewModel = koinViewModel<SplashScreenViewModel>()

				SplashScreenRoot(
					viewModel = viewModel,
					onCompleteSplash = {
						navController.navigate(Route.Home)
					}
				)
			}

			composable<Route.Home>(

			) {

				HomeScreenRoot()
			}
		}
	}
}