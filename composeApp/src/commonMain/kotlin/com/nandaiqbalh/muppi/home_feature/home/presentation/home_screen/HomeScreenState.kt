package com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen

import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState

data class HomeScreenState(
	var nowPlaying: UiState<List<Movie>> = UiState.Initial,
	val topRated: UiState<List<Movie>> = UiState.Initial,
	val upcomingMovies: UiState<List<Movie>> = UiState.Initial,
	val onAirTv: UiState<List<Movie>> = UiState.Initial,
)
