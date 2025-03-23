package com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen

import com.nandaiqbalh.muppi.core.domain.model.DetailMovie

sealed interface DetailAction {

	data object OnClickBack : DetailAction

	data class OnClickCast(val id: Int) : DetailAction

	data class OnClickSimilar(val id: Int) : DetailAction

	data class OnClickSave(val movie: DetailMovie) : DetailAction

}