package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie

data class ExploreState(
	val moviesState: UiState<List<Movie>> = UiState.Initial,
	val nextPageState: UiState<Boolean> = UiState.Initial,
	val movies: List<Movie> = emptyList(),
	val page: Int = 1,
	val keyword: String = "",
	val genres: List<Int> = emptyList(),
	val isMovie: Boolean = true,
	val isSearchFieldVisible: Boolean = false
)