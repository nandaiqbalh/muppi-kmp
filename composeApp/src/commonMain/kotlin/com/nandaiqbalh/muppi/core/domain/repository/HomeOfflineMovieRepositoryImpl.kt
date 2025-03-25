package com.nandaiqbalh.muppi.core.domain.repository

import com.nandaiqbalh.muppi.core.data.local_database.dao.HomeOfflineMovieDao
import com.nandaiqbalh.muppi.core.data.mapper.toMovie
import com.nandaiqbalh.muppi.core.data.mapper.toNowPlayingEntity
import com.nandaiqbalh.muppi.core.data.mapper.toSeriesOnAirEntity
import com.nandaiqbalh.muppi.core.data.mapper.toTopRatedEntity
import com.nandaiqbalh.muppi.core.data.mapper.toUpcomingEntity
import com.nandaiqbalh.muppi.core.data.repository.HomeOfflineMovieRepository
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeOfflineMovieRepositoryImpl(
	private val dao: HomeOfflineMovieDao,
) : HomeOfflineMovieRepository {

	// Now Playing Movies
	override suspend fun upsertNowPlayingMovie(movie: Movie) {
		dao.upsertNowPlayingMovie(movie.toNowPlayingEntity())  // Convert and upsert
	}

	override fun getNowPlayingMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return dao.getNowPlayingMovies(isMovie, query)
			.map { it.map { entity -> entity.toMovie() }.reversed() }  // Map entities to movies
	}

	// Upcoming Movies
	override suspend fun upsertUpcomingMovie(movie: Movie) {
		dao.upsertUpcomingMovie(movie.toUpcomingEntity())  // Convert and upsert
	}

	override fun getUpcomingMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return dao.getUpcomingMovies(isMovie, query)
			.map { it.map { entity -> entity.toMovie() }.reversed() }  // Map entities to movies
	}


	// Top Rated Movies
	override suspend fun upsertTopRatedMovie(movie: Movie) {
		dao.upsertTopRatedMovie(movie.toTopRatedEntity())  // Convert and upsert
	}

	override fun getTopRatedMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return dao.getTopRatedMovies(isMovie, query)
			.map { it.map { entity -> entity.toMovie() }.reversed() }  // Map entities to movies
	}

	// Series On Air Movies
	override suspend fun upsertSeriesOnAirMovie(movie: Movie) {
		dao.upsertSeriesOnAirMovie(movie.toSeriesOnAirEntity())  // Convert and upsert
	}

	override fun getSeriesOnAirMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return dao.getSeriesOnAirMovies(isMovie, query)
			.map { it.map { entity -> entity.toMovie() }.reversed() }  // Map entities to movies
	}
}
