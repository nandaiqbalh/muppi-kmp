package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.data.dummies.dummyCastList
import com.nandaiqbalh.muppi.core.data.dummies.dummyMovie
import com.nandaiqbalh.muppi.core.data.dummies.dummyMovies
import com.nandaiqbalh.muppi.core.domain.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(

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

	init {
		dummyDetail()
		dummyCasts()
		dummySimilarMovies()
	}

	private fun dummyDetail(){

		viewModelScope.launch {
			updateState { it.copy(detailMovie = UiState.Loading) }

			delay(3000)
			// Execute the use case
//			val uiState = getNowPlayingMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(detailMovie = UiState.Success(dummyMovie)) }

		}

	}

	private fun dummyCasts(){

		viewModelScope.launch {
			updateState { it.copy(casts = UiState.Loading) }

			delay(5000)
			// Execute the use case
//			val uiState = getNowPlayingMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(casts = UiState.Success(dummyCastList)) }

		}

	}

	private fun dummySimilarMovies(){

		viewModelScope.launch {
			updateState { it.copy(similarMovies = UiState.Loading) }

			delay(7000)
			// Execute the use case
//			val uiState = getNowPlayingMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(similarMovies = UiState.Success(dummyMovies)) }

		}

	}


	private fun updateState(update: (DetailMovieState) -> DetailMovieState) {
		_state.value = update(_state.value)
	}

}