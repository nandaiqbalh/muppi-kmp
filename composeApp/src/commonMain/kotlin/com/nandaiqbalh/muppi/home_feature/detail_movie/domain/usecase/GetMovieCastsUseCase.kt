package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailMovieRepository

interface GetMovieCastsUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US", isMovie: Boolean): UiState<List<Cast>>
}

class GetMovieCastsUseCaseImpl(
	private val repository: DetailMovieRepository,
) : GetMovieCastsUseCase {

	override suspend fun execute(movieId: Int, language: String, isMovie: Boolean):  UiState<List<Cast>> {
		var resultState:  UiState<List<Cast>> = UiState.Loading

		repository.getMovieCasts(movieId, language, isMovie).collect { result ->
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