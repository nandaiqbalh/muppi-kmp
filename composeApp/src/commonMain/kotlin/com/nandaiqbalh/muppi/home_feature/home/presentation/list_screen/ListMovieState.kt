package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie

data class ListMovieState(
	val movieState: UiState<List<Movie>> = UiState.Initial,
	val titleScreen: String = "Top Rated"
)