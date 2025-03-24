package com.nandaiqbalh.muppi.saved_feature.domain.usecase

import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.saved_feature.domain.repository.SavedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SavedMovieUseCase {

	// Insert a movie into the repository
	suspend fun insertMovie(movie: Movie): UiState<Boolean>

	// Get movies based on filters
	fun getMovies(isMovie: Boolean? = null, genreIds: List<Int>? = null, query: String? = null): Flow<UiState<List<Movie>>>

	// Get movie details by ID
	suspend fun getMovieDetails(id: Int): UiState<Movie?>

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
	override fun getMovies(isMovie: Boolean?, genreIds: List<Int>?, query: String?): Flow<UiState<List<Movie>>> {
		return flow {
			try {
				savedMovieRepository.getMovies(isMovie, genreIds, query).collect { movieList ->
					emit(UiState.Success(movieList))  // Successfully fetched movies
				}
			} catch (e: Exception) {
				emit(UiState.Error("Error fetching movies", emptyList()))  // Error handling
			}
		}
	}

	// Get movie details by ID
	override suspend fun getMovieDetails(id: Int): UiState<Movie?> {
		return try {
			val movie = savedMovieRepository.getSavedMovie(id)
			if (movie != null) {
				UiState.Success(movie)  // Successfully fetched movie details
			} else {
				UiState.Error("Movie not found", null)  // Movie not found error
			}
		} catch (e: Exception) {
			UiState.Error("Error fetching movie details", null)  // Error handling
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
