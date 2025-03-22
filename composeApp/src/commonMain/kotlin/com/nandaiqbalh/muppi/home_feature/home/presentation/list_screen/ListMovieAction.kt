package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen

sealed interface ListMovieAction {

	data object OnClickBack: ListMovieAction

	data class OnClickItem(val id: Int, val isMovie: Boolean): ListMovieAction

	data object OnClickSearch: ListMovieAction

}