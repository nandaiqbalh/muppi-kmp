package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailMovieRepository

interface GetDetailMovieUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US", isMovie: Boolean): UiState<DetailMovie>
}

class GetDetailMovieUseCaseImpl(
	private val repository: DetailMovieRepository,
) : GetDetailMovieUseCase {

	override suspend fun execute(movieId: Int, language: String, isMovie: Boolean): UiState<DetailMovie> {
		var resultState: UiState<DetailMovie> = UiState.Loading

		repository.getDetailMovie(movieId, language, isMovie).collect { result ->
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
