package com.nandaiqbalh.muppi.explore_feature.domain.usecase

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.explore_feature.domain.repository.ExploreRepository

interface ExploreMovieOrTvUseCase {
	suspend fun execute(
		page: Int = 1,
		isMovie: Boolean,
		language: String = "en-US",
		genres: String,
		keyword: String
	): UiState<List<Movie>>
}

class ExploreMovieOrTvUseCaseImpl(
	private val repository: ExploreRepository,
) : ExploreMovieOrTvUseCase {

	override suspend fun execute(
		page: Int,
		isMovie: Boolean,
		language: String,
		genres: String,
		keyword: String,
	): UiState<List<Movie>> {
		var resultState: UiState<List<Movie>> = UiState.Loading

		repository.exploreMovieOrTv(page, language, isMovie, genres, keyword).collect { result ->
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