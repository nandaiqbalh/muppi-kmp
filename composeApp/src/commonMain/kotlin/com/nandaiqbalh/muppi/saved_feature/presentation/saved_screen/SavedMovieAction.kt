package com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen


interface SavedMovieAction {
	data class GetSavedMovies(
		val isMovie: Boolean = true,
		val query: String
	): SavedMovieAction

	data class OnReachBottom(val page: Int): SavedMovieAction

	data class OnClickItem(val id: Int): SavedMovieAction

	data class OnClickSearchIcon(val isVisible: Boolean): SavedMovieAction

	data class OnQueryChange(val keyword: String): SavedMovieAction

	data class OnSelectType(val isMovie: Boolean): SavedMovieAction

}