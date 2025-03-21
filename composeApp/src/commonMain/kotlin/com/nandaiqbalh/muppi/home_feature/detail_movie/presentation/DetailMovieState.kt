package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation

import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.presentation.UiState

data class DetailMovieState(
	val detailMovie: UiState<DetailMovie> = UiState.Initial,
	val casts: UiState<List<Cast>> = UiState.Initial,
	val similarMovies: UiState<List<Movie>> = UiState.Initial,
)