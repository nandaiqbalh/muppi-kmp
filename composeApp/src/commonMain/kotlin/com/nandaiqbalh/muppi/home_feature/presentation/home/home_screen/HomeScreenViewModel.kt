package com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetNowPlayingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetOnAirTVUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetTopRatedMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
	private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
	private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
	private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
	private val getOnAirTVUseCase: GetOnAirTVUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(HomeScreenState())
	val state: StateFlow<HomeScreenState> get() = _state

	fun onAction(action: HomeScreenAction) {

		when (action) {
			is HomeScreenAction.OnGetTopRated -> {
				getTopRatedMovies()
			}

			is HomeScreenAction.OnGetUpcomingMovies -> {
				getUpcomingMovies()
			}

			is HomeScreenAction.OnGetNowPlaying -> {
				getNowPlayingMovies()
			}

			is HomeScreenAction.OnGetSeriesOnAir -> {
				getOnAirTV()
			}

			else -> {}
		}
	}

	private fun getNowPlayingMovies(page: Int = 1) {
		viewModelScope.launch {
			updateState { it.copy(nowPlaying = UiState.Loading) }

			// Execute the use case
			val uiState = getNowPlayingMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(nowPlaying = uiState) }

		}
	}

	private fun getTopRatedMovies(page: Int = 1) {
		viewModelScope.launch {
			updateState { it.copy(topRated = UiState.Loading) }

			// Execute the use case
			val uiState = getTopRatedMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(topRated = uiState) }

		}
	}

	private fun getUpcomingMovies(page: Int = 1) {
		viewModelScope.launch {
			updateState { it.copy(upcomingMovies = UiState.Loading) }

			// Execute the use case
			val uiState = getUpcomingMoviesUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(upcomingMovies = uiState) }

		}
	}

	private fun getOnAirTV(page: Int = 1) {
		viewModelScope.launch {
			updateState { it.copy(onAirTv = UiState.Loading) }

			// Execute the use case
			val uiState = getOnAirTVUseCase.execute(page)

			// Update the UI state
			updateState { it.copy(onAirTv = uiState) }

		}
	}

	private fun updateState(update: (HomeScreenState) -> HomeScreenState) {
		_state.value = update(_state.value)
	}

}