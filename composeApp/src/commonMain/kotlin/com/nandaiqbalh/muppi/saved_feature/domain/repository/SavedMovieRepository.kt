package com.nandaiqbalh.muppi.saved_feature.domain.repository

import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface SavedMovieRepository {

	// Upsert a movie into the database
	suspend fun upsertMovie(movie: Movie)

	// Get movies based on filtering criteria
	fun getMovies(isMovie: Boolean? = null, genreIds: List<Int>? = null, query: String? = null): Flow<List<Movie>>

	// Get a specific movie by ID
	suspend fun getSavedMovie(id: Int): Movie?

	// Delete a movie by ID
	suspend fun deleteSavedMovie(id: Int): Boolean

	// Check if a movie is marked as a favorite
	suspend fun isFavoriteMovie(id: Int): Boolean
}