package com.nandaiqbalh.muppi.home_feature.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.home_feature.domain.repository.DetailRepository

interface GetSimilarUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US", isMovie: Boolean): UiState<List<Movie>>
}

class GetSimilarUseCaseImpl(
	private val repository: DetailRepository,
) : GetSimilarUseCase {

	override suspend fun execute(movieId: Int, language: String, isMovie: Boolean): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Loading

		repository.getSimilar(movieId, language, isMovie).collect { result ->
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