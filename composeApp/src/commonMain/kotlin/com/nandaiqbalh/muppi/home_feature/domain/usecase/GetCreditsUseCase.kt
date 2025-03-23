package com.nandaiqbalh.muppi.home_feature.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.home_feature.domain.repository.DetailRepository

interface GetCreditsUseCase {
	suspend fun execute(personId: Int, language: String = "en-US", isMovie: Boolean): UiState<List<Movie>>
}

class GetCreditsUseCaseImpl(
	private val repository: DetailRepository,
) : GetCreditsUseCase {

	override suspend fun execute(personId: Int, language: String, isMovie: Boolean): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Loading

		repository.getCombinedCredits(personId, language, isMovie).collect { result ->
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