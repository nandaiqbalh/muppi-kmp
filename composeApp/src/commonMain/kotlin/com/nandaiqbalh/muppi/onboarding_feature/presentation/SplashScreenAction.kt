package com.nandaiqbalh.muppi.onboarding_feature.presentation

sealed interface SplashScreenAction {
	data object OnCompleteSplashScreen: SplashScreenAction
}