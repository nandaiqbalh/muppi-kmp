package com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen

sealed interface HomeScreenAction {

	data class  OnClickItem(val id: Int, val isMovie: Boolean) : HomeScreenAction

	data class OnClickSeeAll(val source: Int): HomeScreenAction

	data object OnGetNowPlaying: HomeScreenAction

	data object OnGetTopRated: HomeScreenAction

	data object OnGetUpcomingMovies: HomeScreenAction

	data object OnGetSeriesOnAir: HomeScreenAction

}