package com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetDetailUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetCastsUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetSimilarUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
	private val getDetailUseCase: GetDetailUseCase,
	private val getSimilarUseCase: GetSimilarUseCase,
	private val getCastsUseCase: GetCastsUseCase,
	private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(DetailState())
	val state: StateFlow<DetailState> get() = _state

	fun onAction(action: DetailAction) {
		when (action) {
			is DetailAction.OnClickBack -> {

			}

			else -> {}
		}
	}

	fun getDetailMovie(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(detailMovie = UiState.Loading) }

			// Execute the use case
			val uiState = getDetailUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(detailMovie = uiState) }

		}
	}

	fun getSimilarMovies(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(similarMovies = UiState.Loading) }

			// Execute the use case
			val uiState = getSimilarUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(similarMovies = uiState) }

		}
	}

	fun getMovieCasts(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(casts = UiState.Loading) }

			// Execute the use case
			val uiState = getCastsUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(casts = uiState) }

		}
	}

	fun getVideos(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(videos = UiState.Loading) }

			// Execute the use case
			val uiState = getVideosUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(videos = uiState) }

		}
	}

	private fun updateState(update: (DetailState) -> DetailState) {
		_state.value = update(_state.value)
	}

}