package com.nandaiqbalh.muppi.saved_feature.domain.usecase

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.saved_feature.domain.repository.SavedMovieRepository
import kotlinx.coroutines.flow.first

interface SavedMovieUseCase {

	// Insert a movie into the repository
	suspend fun insertMovie(movie: Movie): UiState<Boolean>

	// Get movies based on filters
	suspend fun getMovies(isMovie: Boolean? = null, query: String? = null): UiState<List<Movie>>

	// Delete a movie by ID
	suspend fun deleteMovie(id: Int): UiState<Boolean>

	// Check if a movie is marked as a favorite
	suspend fun checkIsFavorite(id: Int): UiState<Boolean>
}


class SavedMovieUseCaseImpl(
	private val savedMovieRepository: SavedMovieRepository
) : SavedMovieUseCase {

	// Insert a movie
	override suspend fun insertMovie(movie: Movie): UiState<Boolean> {
		return try {
			savedMovieRepository.upsertMovie(movie)  // Try to insert the movie
			UiState.Success(true)  // Successfully inserted the movie, so return true
		} catch (e: Exception) {
			UiState.Error("Error inserting movie", false)  // If an error occurs, return false
		}
	}

	// Get movies based on filters
	override suspend fun getMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>> {
		return try {
			// Collect the Flow to get the result
			val movieList = savedMovieRepository.getMovies(isMovie, query).first() // Collecting the first item from the Flow

			// Return success with the movie list
			UiState.Success(movieList)
		} catch (e: Exception) {
			// Handle error and return a UiState.Error
			UiState.Error("Error fetching movies", emptyList())
		}
	}

	// Delete a movie by ID
	override suspend fun deleteMovie(id: Int): UiState<Boolean> {
		return try {
			savedMovieRepository.deleteSavedMovie(id)
			UiState.Success(true)  // Successfully deleted the movie
		} catch (e: Exception) {
			UiState.Error("Error deleting movie", false)  // Error handling
		}
	}

	// Check if a movie is a favorite
	override suspend fun checkIsFavorite(id: Int): UiState<Boolean> {
		return try {
			val isFavorite = savedMovieRepository.isFavoriteMovie(id)
			UiState.Success(isFavorite)  // Return whether the movie is a favorite
		} catch (e: Exception) {
			UiState.Error("Error checking favorite status", false)  // Error handling
		}
	}
}
