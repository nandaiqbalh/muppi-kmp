package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen

import androidx.lifecycle.ViewModel
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetNowPlayingMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetOnAirTVUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetTopRatedMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.home.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListMovieViewModel(
	private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
	private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
	private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
	private val getOnAirTVUseCase: GetOnAirTVUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(ListMovieState())
	val state: StateFlow<ListMovieState> get() = _state

	fun onAction(action: ListMovieAction) {
		when (action) {

			else -> {}
		}
	}

	fun updateState(update: (ListMovieState) -> ListMovieState) {
		_state.value = update(_state.value)
	}

}