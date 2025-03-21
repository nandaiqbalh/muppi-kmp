package com.nandaiqbalh.muppi.app

import kotlinx.serialization.Serializable

sealed interface Route {

	@Serializable
	data object OnboardingGraph : Route

	@Serializable
	data object SplashScreen : Route

	@Serializable
	data object HomeGraph: Route

	@Serializable
	data object HomeScreen: Route

	@Serializable
	data object DetailMovieScreen: Route

	@Serializable
	data object ExploreGraph: Route

	@Serializable
	data object ExploreScreen: Route

	@Serializable
	data object SavedGraph: Route

	@Serializable
	data object SavedScreen: Route
}