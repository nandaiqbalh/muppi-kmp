package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.data.dummies.dummyCastList
import com.nandaiqbalh.muppi.core.data.dummies.dummyMovie
import com.nandaiqbalh.muppi.core.data.dummies.dummyMovies
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetDetailMovieUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetMovieCastsUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetSimilarMoviesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(
	private val getDetailMovieUseCase: GetDetailMovieUseCase,
	private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
	private val getMovieCastsUseCase: GetMovieCastsUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(DetailMovieState())
	val state: StateFlow<DetailMovieState> get() = _state

	fun onAction(action: DetailMovieAction) {
		when (action) {
			is DetailMovieAction.OnClickBack -> {

			}

			else -> {}
		}
	}

	fun getDetailMovie(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(detailMovie = UiState.Loading) }

			// Execute the use case
			val uiState = getDetailMovieUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(detailMovie = uiState) }

		}
	}

	fun getSimilarMovies(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(similarMovies = UiState.Loading) }

			// Execute the use case
			val uiState = getSimilarMoviesUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(similarMovies = uiState) }

		}
	}

	fun getMovieCasts(movieId: Int, isMovie: Boolean) {
		viewModelScope.launch {
			updateState { it.copy(casts = UiState.Loading) }

			// Execute the use case
			val uiState = getMovieCastsUseCase.execute(movieId, isMovie = isMovie)

			// Update the UI state
			updateState { it.copy(casts = uiState) }

		}
	}


	private fun updateState(update: (DetailMovieState) -> DetailMovieState) {
		_state.value = update(_state.value)
	}

}