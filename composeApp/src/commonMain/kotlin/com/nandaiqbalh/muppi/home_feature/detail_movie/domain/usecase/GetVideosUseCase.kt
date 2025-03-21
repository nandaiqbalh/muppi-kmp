package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Video
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailRepository

interface GetVideosUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US", isMovie: Boolean): UiState<List<Video>>
}

class GetVideosUseCaseImpl(
	private val repository: DetailRepository,
) : GetVideosUseCase {

	override suspend fun execute(movieId: Int, language: String, isMovie: Boolean): UiState<List<Video>> {
		var resultState: UiState<List<Video>> = UiState.Loading

		repository.getVideos(movieId, language, isMovie).collect { result ->
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