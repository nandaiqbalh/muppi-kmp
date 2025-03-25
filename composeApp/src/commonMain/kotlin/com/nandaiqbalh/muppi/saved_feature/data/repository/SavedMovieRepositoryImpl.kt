package com.nandaiqbalh.muppi.saved_feature.data.repository

import com.nandaiqbalh.muppi.core.data.mapper.toMovie
import com.nandaiqbalh.muppi.core.data.mapper.toMovieEntity
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDao
import com.nandaiqbalh.muppi.saved_feature.domain.repository.SavedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedMovieRepositoryImpl(
	private val savedMovieDao: SavedMovieDao
) : SavedMovieRepository {

	// Upsert (insert/update) a movie into the database
	override suspend fun upsertMovie(movie: Movie) {
		val movieEntity = movie.toMovieEntity()  // Convert Movie to MovieEntity
		savedMovieDao.upsert(movieEntity)  // Insert or update the movie in the database
	}

	// Get movies based on filter criteria
	override fun getMovies(isMovie: Boolean?, query: String?): Flow<List<Movie>> {
		return savedMovieDao.getMovies(isMovie, query)
			.map { movieEntities -> movieEntities.map { it.toMovie() } } // Map MovieEntity to Movie
	}

	// Get a specific movie by ID
	override suspend fun getSavedMovie(id: Int): Movie? {
		return savedMovieDao.getSavedMovie(id)?.toMovie() // Map MovieEntity to Movie
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
