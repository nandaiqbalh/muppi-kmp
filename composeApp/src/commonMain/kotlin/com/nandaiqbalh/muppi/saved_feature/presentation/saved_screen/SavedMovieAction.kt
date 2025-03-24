package com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen


interface SavedMovieAction {
	data class ExploreMovieOrTv(
		val isFirstLoad: Boolean = true,
		val page: Int = 1,
		val isMovie: Boolean = true,
		val language: String = "en-US",
	): SavedMovieAction

	data class OnReachBottom(val page: Int): SavedMovieAction

	data class OnClickItem(val id: Int): SavedMovieAction

	data class OnClickSearchIcon(val isVisible: Boolean): SavedMovieAction

	data class OnQueryChange(val keyword: String): SavedMovieAction

	data class OnSelectType(val isMovie: Boolean): SavedMovieAction

	data class OnSelectGenres(val genres: List<Int>): SavedMovieAction
}