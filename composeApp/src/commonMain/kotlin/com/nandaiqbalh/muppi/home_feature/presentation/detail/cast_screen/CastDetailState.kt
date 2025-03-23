package com.nandaiqbalh.muppi.home_feature.presentation.detail.cast_screen

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.core.domain.model.Movie

data class CastDetailState(
	val castDetailState: UiState<CastDetail> = UiState.Initial,
	val moviesState: UiState<List<Movie>> = UiState.Initial
)