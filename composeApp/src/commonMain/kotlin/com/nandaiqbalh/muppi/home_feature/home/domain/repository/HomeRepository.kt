package com.nandaiqbalh.muppi.home_feature.home.domain.repository

import com.nandaiqbalh.muppi.core.data.remote.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

	suspend fun getNowPlayingMovies(page: Int, language: String = "en-US"): Flow<NetworkResult<List<Movie>>>

	suspend fun getTopRatedMovies(page: Int, language: String = "en-US"): Flow<NetworkResult<List<Movie>>>

	suspend fun getUpcomingMovies(page: Int, language: String = "en-US"): Flow<NetworkResult<List<Movie>>>

	suspend fun getOnAirTV(page: Int, language: String = "en-US"): Flow<NetworkResult<List<Movie>>>

}