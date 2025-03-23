package com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen

sealed interface ListMovieAction {

	data object OnClickBack: ListMovieAction

	data class OnClickItem(val id: Int): ListMovieAction

	data object OnClickSearch: ListMovieAction

	data class GetMovieList(val page: Int, val source: Int, val isFirstLoad: Boolean):
		ListMovieAction

}