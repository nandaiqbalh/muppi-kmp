package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation

import com.nandaiqbalh.muppi.core.domain.model.DetailMovie

sealed interface DetailMovieAction {

	data object OnClickBack : DetailMovieAction

	data class OnClickCast(val id: Int) : DetailMovieAction

	data class OnClickSimilarMovie(val id: Int) : DetailMovieAction

	data class OnClickSave(val movie: DetailMovie) : DetailMovieAction

}