package com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen

import com.nandaiqbalh.muppi.core.domain.model.DetailMovie

sealed interface DetailAction {

	data object OnClickBack : DetailAction

	data class OnClickCast(val id: Int) : DetailAction

	data class OnClickSimilar(val id: Int) : DetailAction

	data class OnSaveMovie(val movie: DetailMovie) : DetailAction

	data class OnDeleteMovie(val id: Int) : DetailAction

}