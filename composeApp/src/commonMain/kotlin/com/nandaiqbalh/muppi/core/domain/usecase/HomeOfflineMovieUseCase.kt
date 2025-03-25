package com.nandaiqbalh.muppi.core.domain.usecase

import com.nandaiqbalh.muppi.core.data.repository.HomeOfflineMovieRepository
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import kotlinx.coroutines.flow.first

interface HomeOfflineMovieUseCase {

	// Now Playing Movies
	suspend fun insertNowPlayingMovie(movie: Movie): UiState<Boolean>
	suspend fun getNowPlayingMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>>

	// Upcoming Movies
	suspend fun insertUpcomingMovie(movie: Movie): UiState<Boolean>
	suspend fun getUpcomingMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>>

	// Top Rated Movies
	suspend fun insertTopRatedMovie(movie: Movie): UiState<Boolean>
	suspend fun getTopRatedMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>>

	// Series On Air Movies
	suspend fun insertSeriesOnAirMovie(movie: Movie): UiState<Boolean>
	suspend fun getSeriesOnAirMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>>
}


class HomeOfflineMovieUseCaseImpl(
	private val homeOfflineMovieRepository: HomeOfflineMovieRepository
) : HomeOfflineMovieUseCase {

	// Now Playing Movies
	override suspend fun insertNowPlayingMovie(movie: Movie): UiState<Boolean> {
		return try {
			homeOfflineMovieRepository.upsertNowPlayingMovie(movie)
			UiState.Success(true)
		} catch (e: Exception) {
			UiState.Error("Error inserting Now Playing movie", false)
		}
	}

	override suspend fun getNowPlayingMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>> {
		return try {
			val movies = homeOfflineMovieRepository.getNowPlayingMovies(isMovie, query).first()
			UiState.Success(movies)
		} catch (e: Exception) {
			UiState.Error("Error fetching Now Playing movies", emptyList())
		}
	}

	// Upcoming Movies
	override suspend fun insertUpcomingMovie(movie: Movie): UiState<Boolean> {
		return try {
			homeOfflineMovieRepository.upsertUpcomingMovie(movie)
			UiState.Success(true)
		} catch (e: Exception) {
			UiState.Error("Error inserting Upcoming movie", false)
		}
	}

	override suspend fun getUpcomingMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>> {
		return try {
			val movies = homeOfflineMovieRepository.getUpcomingMovies(isMovie, query).first()
			UiState.Success(movies)
		} catch (e: Exception) {
			UiState.Error("Error fetching Upcoming movies", emptyList())
		}
	}

	// Top Rated Movies
	override suspend fun insertTopRatedMovie(movie: Movie): UiState<Boolean> {
		return try {
			homeOfflineMovieRepository.upsertTopRatedMovie(movie)
			UiState.Success(true)
		} catch (e: Exception) {
			UiState.Error("Error inserting Top Rated movie", false)
		}
	}

	override suspend fun getTopRatedMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>> {
		return try {
			val movies = homeOfflineMovieRepository.getTopRatedMovies(isMovie, query).first()
			UiState.Success(movies)
		} catch (e: Exception) {
			UiState.Error("Error fetching Top Rated movies", emptyList())
		}
	}

	// Series On Air Movies
	override suspend fun insertSeriesOnAirMovie(movie: Movie): UiState<Boolean> {
		return try {
			homeOfflineMovieRepository.upsertSeriesOnAirMovie(movie)
			UiState.Success(true)
		} catch (e: Exception) {
			UiState.Error("Error inserting Series On Air movie", false)
		}
	}

	override suspend fun getSeriesOnAirMovies(isMovie: Boolean?, query: String?): UiState<List<Movie>> {
		return try {
			val movies = homeOfflineMovieRepository.getSeriesOnAirMovies(isMovie, query).first()
			UiState.Success(movies)
		} catch (e: Exception) {
			UiState.Error("Error fetching Series On Air movies", emptyList())
		}
	}

}
