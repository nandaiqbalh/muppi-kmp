package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie

data class ListMovieState(
	val moviesState: UiState<List<Movie>> = UiState.Initial,
	val nextPageState: UiState<Boolean> = UiState.Initial,
	val movies: List<Movie> = emptyList(),
	val source: Int = 0,
	val page: Int = 1
)