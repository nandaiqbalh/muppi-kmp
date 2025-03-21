package com.nandaiqbalh.muppi.di

import com.nandaiqbalh.muppi.core.data.remote.HttpClientFactory
import com.nandaiqbalh.muppi.core.data.remote.setupKermit
import com.nandaiqbalh.muppi.core.getPlatform
import com.nandaiqbalh.muppi.home_feature.detail_movie.data.repository.DetailMovieRepositoryImpl
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailMovieRepository
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetDetailMovieUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetDetailMovieUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetMovieCastsUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetMovieCastsUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetSimilarMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetSimilarMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.DetailMovieViewModel
import com.nandaiqbalh.muppi.home_feature.home.data.repository.HomeRepositoryImpl
import com.nandaiqbalh.muppi.home_feature.home.domain.repository.HomeRepository
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetNowPlayingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetOnAirTVUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetOnAirTVUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetTopRatedMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetTopRatedMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetUpcomingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetUpcomingMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.home.presentation.HomeScreenViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
	single { setupKermit() }

	single {
		HttpClientFactory.create(engine = get(), isDebugMode = getPlatform().isDebugMode(), kermitLogger = get() )
	}

	// repository
	singleOf(::HomeRepositoryImpl).bind<HomeRepository>()
	singleOf(::DetailMovieRepositoryImpl).bind<DetailMovieRepository>()

	// use case
	singleOf(::GetNowPlayingMoviesUseCaseImpl).bind<GetNowPlayingMoviesUseCase>()
	singleOf(::GetTopRatedMoviesUseCaseImpl).bind<GetTopRatedMoviesUseCase>()
	singleOf(::GetUpcomingMoviesUseCaseImpl).bind<GetUpcomingMoviesUseCase>()
	singleOf(::GetOnAirTVUseCaseImpl).bind<GetOnAirTVUseCase>()

	singleOf(::GetDetailMovieUseCaseImpl).bind<GetDetailMovieUseCase>()
	singleOf(::GetSimilarMoviesUseCaseImpl).bind<GetSimilarMoviesUseCase>()
	singleOf(::GetMovieCastsUseCaseImpl).bind<GetMovieCastsUseCase>()

	// view model
	viewModelOf(::SplashScreenViewModel)
	viewModelOf(::HomeScreenViewModel)
	viewModelOf(::DetailMovieViewModel)
}