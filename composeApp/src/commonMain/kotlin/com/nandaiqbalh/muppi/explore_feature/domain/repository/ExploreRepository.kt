package com.nandaiqbalh.muppi.explore_feature.domain.repository

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {

	suspend fun exploreMovieOrTv(
		page: Int = 1,
		language: String = "en-US",
		isMovie: Boolean,
		genres: String,
		keyword: String,
	): Flow<NetworkResult<List<Movie>>>

}