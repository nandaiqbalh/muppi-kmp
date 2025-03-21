package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository

import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {

	suspend fun getDetailMovie(movieId: Int, language: String = "en-US"): Flow<DetailMovie>

	suspend fun getSimilarMovies(movieId: Int, language: String = "en-US"): Flow<List<Movie>>

	suspend fun getMovieCasts(movieId: Int, language: String = "en-US"): Flow<List<Cast>>
}