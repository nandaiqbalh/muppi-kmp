package com.nandaiqbalh.muppi.di

import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModules = module {

	viewModelOf(::SplashScreenViewModel)
	viewModelOf(::HomeScreenViewModel)
}