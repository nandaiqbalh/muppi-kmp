package com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen

import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Video

data class DetailState(
	val detailMovie: UiState<DetailMovie> = UiState.Initial,
	val casts: UiState<List<Cast>> = UiState.Initial,
	val similarMovies: UiState<List<Movie>> = UiState.Initial,
	val videos: UiState<List<Video>> = UiState.Initial,
	var saveState: UiState<Boolean> = UiState.Initial,
	var deleteState: UiState<Boolean> = UiState.Initial,
	val isFavorite: Boolean = false
)