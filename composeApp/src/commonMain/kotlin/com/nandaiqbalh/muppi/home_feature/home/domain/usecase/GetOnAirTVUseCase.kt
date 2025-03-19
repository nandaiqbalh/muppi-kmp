package com.nandaiqbalh.muppi.home_feature.home.domain.usecase

import com.nandaiqbalh.muppi.core.data.remote.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.presentation.UiState
import com.nandaiqbalh.muppi.home_feature.home.domain.repository.HomeRepository

interface GetOnAirTVUseCase {
	suspend fun execute(page: Int, language: String = "en-US"): UiState<List<Movie>>
}

class GetOnAirTVUseCaseImpl(
	private val repository: HomeRepository,
) : GetOnAirTVUseCase {

	override suspend fun execute(page: Int, language: String): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Initial

		repository.getOnAirTV(page, language).collect { result ->
			resultState = when (result) {
				is NetworkResult.Loading -> UiState.Loading
				is NetworkResult.Success -> {
					UiState.Success(result.data)
				}

				is NetworkResult.Error -> {
					val dataError = emptyList<Movie>()

					UiState.Error(data = dataError, errorMessage = result.message)
				}
			}
		}
		return resultState
	}
}
