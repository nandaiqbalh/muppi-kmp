package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailRepository

interface GetCastDetailUseCase {
	suspend fun execute(movieId: Int): UiState<CastDetail>
}

class GetCastDetailUseCaseImpl(
	private val repository: DetailRepository,
) : GetCastDetailUseCase {

	override suspend fun execute(movieId: Int):  UiState<CastDetail> {
		var resultState:  UiState<CastDetail> = UiState.Loading

		repository.getCastDetail(movieId).collect { result ->
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