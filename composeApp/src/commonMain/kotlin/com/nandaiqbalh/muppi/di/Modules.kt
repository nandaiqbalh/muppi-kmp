package com.nandaiqbalh.muppi.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.nandaiqbalh.muppi.core.data.local_database.DatabaseFactory
import com.nandaiqbalh.muppi.core.data.remote.HttpClientFactory
import com.nandaiqbalh.muppi.core.data.remote.setupKermit
import com.nandaiqbalh.muppi.core.getPlatform
import com.nandaiqbalh.muppi.explore_feature.data.repository.ExploreRepositoryImpl
import com.nandaiqbalh.muppi.explore_feature.domain.repository.ExploreRepository
import com.nandaiqbalh.muppi.explore_feature.domain.usecase.ExploreMovieOrTvUseCase
import com.nandaiqbalh.muppi.explore_feature.domain.usecase.ExploreMovieOrTvUseCaseImpl
import com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.ExploreViewModel
import com.nandaiqbalh.muppi.home_feature.data.repository.DetailRepositoryImpl
import com.nandaiqbalh.muppi.home_feature.domain.repository.DetailRepository
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCastDetailUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCastDetailUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetDetailUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetDetailUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCastsUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCastsUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCreditsUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCreditsUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetSimilarUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetSimilarUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetVideosUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetVideosUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.presentation.detail.cast_screen.CastDetailViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen.DetailViewModel
import com.nandaiqbalh.muppi.home_feature.data.repository.HomeRepositoryImpl
import com.nandaiqbalh.muppi.home_feature.domain.repository.HomeRepository
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetNowPlayingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetNowPlayingMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetOnAirTVUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetOnAirTVUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetTopRatedMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetTopRatedMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetUpcomingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetUpcomingMoviesUseCaseImpl
import com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen.HomeScreenViewModel
import com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen.ListMovieViewModel
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreenViewModel
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDatabase
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
	singleOf(::DetailRepositoryImpl).bind<DetailRepository>()
	singleOf(::ExploreRepositoryImpl).bind<ExploreRepository>()

	// use case
	singleOf(::GetNowPlayingMoviesUseCaseImpl).bind<GetNowPlayingMoviesUseCase>()
	singleOf(::GetTopRatedMoviesUseCaseImpl).bind<GetTopRatedMoviesUseCase>()
	singleOf(::GetUpcomingMoviesUseCaseImpl).bind<GetUpcomingMoviesUseCase>()
	singleOf(::GetOnAirTVUseCaseImpl).bind<GetOnAirTVUseCase>()

	singleOf(::GetDetailUseCaseImpl).bind<GetDetailUseCase>()
	singleOf(::GetSimilarUseCaseImpl).bind<GetSimilarUseCase>()
	singleOf(::GetCastsUseCaseImpl).bind<GetCastsUseCase>()
	singleOf(::GetVideosUseCaseImpl).bind<GetVideosUseCase>()

	singleOf(::GetCastDetailUseCaseImpl).bind<GetCastDetailUseCase>()
	singleOf(::GetCreditsUseCaseImpl).bind<GetCreditsUseCase>()

	singleOf(::ExploreMovieOrTvUseCaseImpl).bind<ExploreMovieOrTvUseCase>()

	// view model
	viewModelOf(::SplashScreenViewModel)
	viewModelOf(::HomeScreenViewModel)
	viewModelOf(::DetailViewModel)
	viewModelOf(::CastDetailViewModel)
	viewModelOf(::ListMovieViewModel)

	viewModelOf(::ExploreViewModel)

	// local database
	single {
		get<DatabaseFactory>().create()
			.setDriver(BundledSQLiteDriver())
			.build()
	}

	single {
		get<SavedMovieDatabase>().savedMovieDao
	}

}