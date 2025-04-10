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
import com.nandaiqbalh.muppi.core.utils.orFalse
import com.nandaiqbalh.muppi.core.utils.orTrue
import com.nandaiqbalh.muppi.core.utils.orZero
import com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.ExploreScreenRoot
import com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.ExploreViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.detail.cast_screen.CastDetailScreenRoot
import com.nandaiqbalh.muppi.home_feature.presentation.detail.cast_screen.CastDetailViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen.DetailScreenRoot
import com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen.DetailViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen.HomeScreenRoot
import com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen.HomeScreenViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen.ListMovieScreenRoot
import com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen.ListMovieViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenRoot
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen.SavedMovieScreenRoot
import com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen.SavedMovieViewModel
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
							onClickItem = { id, isMovie ->
								// Pass the movieId to the DetailMovieScreen via the navigation route
								navController.navigate(Route.DetailMovieScreen(id, isMovie))
							},
							onClickSeeAll = { source ->
								navController.navigate(Route.ListMovieScreen(source = source))
							}
						)
					}

					composable<Route.ListMovieScreen> {
						val viewModel = koinViewModel<ListMovieViewModel>()

						// Retrieve the passed argument from the navigation route
						val source = it.arguments?.getInt("source")?.orZero()

						ListMovieScreenRoot(
							viewModel = viewModel,
							source = source.orZero(),
							onClickBack = {
								navController.popBackStack()
							},
							onClickSearch = {
								navController.navigate(Route.ExploreGraph) {
									popUpTo(Route.HomeGraph) {
										inclusive = false  // This ensures we don't pop HomeGraph itself, just the previous entries
									}
									launchSingleTop = true // Avoid multiple instances of the same screen									launchSingleTop = true // Optional: Avoid multiple instances of the same screen
								}
							},
							onClickMovieItem = { id, isMovie ->
								navController.navigate(Route.DetailMovieScreen(id, isMovie))
							}
						)
					}

					composable<Route.DetailMovieScreen> {
						val viewModel = koinViewModel<DetailViewModel>()

						// Get the movieId from the nav arguments
						val movieId = it.arguments?.getInt("movieId")?.orZero()
						val isMovie = it.arguments?.getBoolean("isMovie")?.orTrue()

						DetailScreenRoot(
							viewModel = viewModel,
							movieId = movieId.orZero(),
							isMovie = isMovie.orFalse(),
							onClickBack = {
								navController.popBackStack()
							},
							onClickCast = { id ->
								navController.navigate(Route.CastDetailScreen(id))
							},
							onClickSimilarMovie = { similarMovieId, isSimilarMovie ->
								navController.navigate(Route.DetailMovieScreen(similarMovieId, isMovie = isSimilarMovie))
							}
						)
					}

					composable<Route.CastDetailScreen> {
						val viewModel = koinViewModel<CastDetailViewModel>()

						// Get the movieId from the nav arguments
						val castId = it.arguments?.getInt("castId")?.orZero()
						val isMovie = it.arguments?.getBoolean("isMovie")?.orTrue()

						CastDetailScreenRoot(
							viewModel = viewModel,
							castId = castId.orZero(),
							isMovie = isMovie.orTrue(),
							onClickBack = {
								navController.popBackStack()
							},
							onClickMovie = { id, isSimilarMovie ->
								navController.navigate(Route.DetailMovieScreen(id, isMovie = isSimilarMovie))
							}
						)
					}
				}

				// explore
				navigation<Route.ExploreGraph>(
					startDestination = Route.ExploreScreen
				) {
					composable<Route.ExploreScreen> {

						val viewModel = koinViewModel<ExploreViewModel>()

						ExploreScreenRoot(
							viewModel = viewModel,
							onClickMovieItem = { id, isMovie ->
								navController.navigate(Route.DetailMovieScreen(id, isMovie = isMovie))
							}
						)
					}
				}

				// saved
				navigation<Route.SavedGraph>(
					startDestination = Route.SavedScreen
				) {
					composable<Route.SavedScreen> {
						val viewModel = koinViewModel<SavedMovieViewModel>()

						SavedMovieScreenRoot(
							viewModel = viewModel,
							onClickItem = { id, isMovie ->
								navController.navigate(Route.DetailMovieScreen(id, isMovie = isMovie))
							}
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
