package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.cast_screen

sealed interface CastDetailAction {

	data class OnClickMovie(val id: Int): CastDetailAction

	data object OnClickBack: CastDetailAction
}