package com.nandaiqbalh.muppi.home_feature.domain.usecase

import com.nandaiqbalh.muppi.core.data.mapper.toDetailMovie
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.home_feature.domain.repository.DetailRepository
import com.nandaiqbalh.muppi.saved_feature.domain.repository.SavedMovieRepository

interface GetDetailUseCase {
	suspend fun execute(movieId: Int, language: String = "en-US", isMovie: Boolean): UiState<DetailMovie>
}

class GetDetailUseCaseImpl(
	private val repository: DetailRepository,
	private val savedMovieRepository: SavedMovieRepository
) : GetDetailUseCase {

	override suspend fun execute(movieId: Int, language: String, isMovie: Boolean): UiState<DetailMovie> {
		var resultState: UiState<DetailMovie> = UiState.Loading

		try {
			val movie = savedMovieRepository.getSavedMovie(movieId)
			if (movie != null) {
				resultState = UiState.Success(movie.toDetailMovie())  // Successfully fetched movie details
			} else {
				repository.getDetail(movieId, language, isMovie).collect { result ->
					resultState = when (result) {
						is NetworkResult.Loading -> UiState.Loading
						is NetworkResult.Success -> {
							UiState.Success(result.data)
						}

						is NetworkResult.Error -> {
							UiState.Error(errorMessage = result.message)
						}
					}
				}			}
		} catch (e: Exception) {
			repository.getDetail(movieId, language, isMovie).collect { result ->
				resultState = when (result) {
					is NetworkResult.Loading -> UiState.Loading
					is NetworkResult.Success -> {
						UiState.Success(result.data)
					}

					is NetworkResult.Error -> {
						UiState.Error(errorMessage = result.message)
					}
				}
			}		}

		return resultState
	}
}
