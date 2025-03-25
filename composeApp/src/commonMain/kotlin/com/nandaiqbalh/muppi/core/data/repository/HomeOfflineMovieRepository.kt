package com.nandaiqbalh.muppi.core.data.repository

import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface HomeOfflineMovieRepository {

	// Now Playing Movies
	suspend fun upsertNowPlayingMovie(movie: Movie)
	fun getNowPlayingMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<Movie>>

	// Upcoming Movies
	suspend fun upsertUpcomingMovie(movie: Movie)
	fun getUpcomingMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<Movie>>

	// Top Rated Movies
	suspend fun upsertTopRatedMovie(movie: Movie)
	fun getTopRatedMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<Movie>>

	// Series On Air
	suspend fun upsertSeriesOnAirMovie(movie: Movie)
	fun getSeriesOnAirMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<Movie>>

}
