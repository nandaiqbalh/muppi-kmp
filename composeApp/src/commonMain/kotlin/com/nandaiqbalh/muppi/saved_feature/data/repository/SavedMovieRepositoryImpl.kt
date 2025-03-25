package com.nandaiqbalh.muppi.saved_feature.data.repository

import com.nandaiqbalh.muppi.core.data.local_database.dao.HomeOfflineMovieDao
import com.nandaiqbalh.muppi.core.data.mapper.toMovie
import com.nandaiqbalh.muppi.core.data.mapper.toMovieEntity
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDao
import com.nandaiqbalh.muppi.saved_feature.domain.repository.SavedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedMovieRepositoryImpl(
	private val savedMovieDao: SavedMovieDao,
	private val homeOfflineMovieDao: HomeOfflineMovieDao
) : SavedMovieRepository {

	// Upsert (insert/update) a movie into the database
	override suspend fun upsertMovie(movie: Movie) {
		val movieEntity = movie.toMovieEntity()  // Convert Movie to MovieEntity
		savedMovieDao.upsert(movieEntity)  // Insert or update the movie in the database
	}

	// Get movies based on filter criteria
	override fun getMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return savedMovieDao.getMovies(isMovie, query)
			.map { movieEntities -> movieEntities.map { it.toMovie() }.reversed() } // Map MovieEntity to Movie
	}

	// Get a specific movie by ID
	override suspend fun getDetailMovie(id: Int): Movie? {
		// Attempt to get the movie from the Now Playing DAO first
		var movie = savedMovieDao.getSavedMovie(id)?.toMovie()

		// If not found, try checking from the Now Playing DAO in homeOfflineMovieDao
		if (movie == null) {
			movie = homeOfflineMovieDao.getNowPlayingDetailMovie(id)?.toMovie()
		}

		// If still not found, try checking from the Upcoming DAO
		if (movie == null) {
			movie = homeOfflineMovieDao.getUpcomingDetailMovie(id)?.toMovie()
		}

		// Try checking from the Top Rated DAO
		if (movie == null) {
			movie = homeOfflineMovieDao.getTopRatedDetailMovie(id)?.toMovie()
		}

		// Try checking from the Series On Air DAO
		if (movie == null) {
			movie = homeOfflineMovieDao.getSeriesOnAirDetailMovie(id)?.toMovie()
		}

		return movie
	}



	// Delete a movie by ID
	override suspend fun deleteSavedMovie(id: Int): Boolean {
		val deleteMovie = savedMovieDao.deleteSavedMovie(id)
		return deleteMovie != 0
	}

	// Check if a movie is marked as a favorite (assuming `isMovie` flag is true)
	override suspend fun isFavoriteMovie(id: Int): Boolean {
		val movie = savedMovieDao.getSavedMovie(id)
		return movie != null
	}
}
