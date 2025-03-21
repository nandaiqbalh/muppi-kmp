package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailMovieRepository

interface GetSimilarMoviesUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US"): UiState<List<Movie>>
}

class GetSimilarMoviesUseCaseImpl(
	private val repository: DetailMovieRepository,
) : GetSimilarMoviesUseCase {

	override suspend fun execute(movieId: Int, language: String): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Loading

		repository.getSimilarMovies(movieId, language).collect { result ->
			resultState = when (result) {
				is NetworkResult.Loading -> UiState.Loading
				is NetworkResult.Success -> {
					UiState.Success(result.data)
				}

				is NetworkResult.Error -> {
					UiState.Error(errorMessage = result.message)
				}
			}
		}
		return resultState
	}
}