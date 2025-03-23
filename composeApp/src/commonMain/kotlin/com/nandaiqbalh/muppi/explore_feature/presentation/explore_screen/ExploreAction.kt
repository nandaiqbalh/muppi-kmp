package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen

sealed interface ExploreAction {

	data class ExploreMovieOrTv(
		val isFirstLoad: Boolean = true,
		val page: Int = 1,
		val isMovie: Boolean = true,
		val language: String = "en-US",
	): ExploreAction

	data class OnReachBottom(val page: Int): ExploreAction

	data class OnClickItem(val id: Int): ExploreAction

	data class OnClickSearchIcon(val isVisible: Boolean): ExploreAction

	data class OnQueryChange(val keyword: String): ExploreAction

	data class OnSelectType(val isMovie: Boolean): ExploreAction

	data class OnSelectGenres(val genres: List<Int>): ExploreAction

}