package com.nandaiqbalh.muppi.core.domain.model

import kotlinx.serialization.Serializable

sealed interface SeeAllSource {

	@Serializable
	data object TopRated: SeeAllSource

	@Serializable
	data object UpcomingMovies: SeeAllSource

	@Serializable
	data object SeriesOnAir: SeeAllSource
}