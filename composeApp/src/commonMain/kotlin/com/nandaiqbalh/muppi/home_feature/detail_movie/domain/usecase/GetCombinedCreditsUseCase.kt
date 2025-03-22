package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailRepository

interface GetCombinedCreditsUseCase {
	suspend fun execute(personId: Int, language: String = "en-US"): UiState<List<Movie>>
}

class GetCombinedCreditsUseCaseImpl(
	private val repository: DetailRepository,
) : GetCombinedCreditsUseCase {

	override suspend fun execute(personId: Int, language: String): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Loading

		repository.getCombinedCredits(personId, language).collect { result ->
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