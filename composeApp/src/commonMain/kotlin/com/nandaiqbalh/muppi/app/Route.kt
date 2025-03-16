package com.nandaiqbalh.muppi.app

import kotlinx.serialization.Serializable

sealed interface Route {

	@Serializable
	data object SplashScreen : Route

	@Serializable
	data object Home: Route

}