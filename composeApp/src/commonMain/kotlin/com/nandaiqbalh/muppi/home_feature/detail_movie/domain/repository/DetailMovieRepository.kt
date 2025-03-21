package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

	suspend fun getDetailMovie(movieId: Int, language: String = "en-US"): Flow<NetworkResult<DetailMovie>>

	suspend fun getSimilarMovies(movieId: Int, language: String = "en-US"): Flow<NetworkResult<List<Movie>>>

	suspend fun getMovieCasts(movieId: Int, language: String = "en-US"): Flow<NetworkResult<List<Cast>>>
}