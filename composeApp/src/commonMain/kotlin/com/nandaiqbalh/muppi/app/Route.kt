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
}