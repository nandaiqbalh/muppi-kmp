package com.nandaiqbalh.muppi.home_feature.domain.usecase

import com.nandaiqbalh.muppi.core.data.repository.HomeOfflineMovieRepository
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.domain.repository.HomeRepository
import kotlinx.coroutines.flow.first

interface GetNowPlayingMoviesUseCase {
	suspend fun execute(page: Int, language: String = "en-US"): UiState<List<Movie>>
}

class GetNowPlayingMoviesUseCaseImpl(
	private val repository: HomeRepository,
	private val homeOfflineMovieRepository: HomeOfflineMovieRepository
) : GetNowPlayingMoviesUseCase {

	override suspend fun execute(page: Int, language: String): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Initial

		repository.getNowPlayingMovies(page, language).collect { result ->
			resultState = when (result) {
				is NetworkResult.Loading -> UiState.Loading
				is NetworkResult.Success -> {
					result.data.forEach {
						homeOfflineMovieRepository.upsertNowPlayingMovie(it)
					}

					UiState.Success(result.data)
				}

				is NetworkResult.Error -> {
					try {
						// Collect the Flow to get the result
						val movieList = homeOfflineMovieRepository.getNowPlayingMovies(true, "").first() // Collecting the first item from the Flow

						// Return success with the movie list
						if (movieList.isNotEmpty()){
							UiState.Success(movieList)
						} else {
							UiState.Error("Error fetching movies", emptyList())
						}
					} catch (e: Exception) {
						// Handle error and return a UiState.Error
						UiState.Error("Error fetching movies", emptyList())
					}
				}
			}
		}
		return resultState
	}
}
