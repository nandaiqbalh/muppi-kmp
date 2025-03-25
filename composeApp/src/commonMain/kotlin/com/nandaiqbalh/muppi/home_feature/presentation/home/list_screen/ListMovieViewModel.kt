package com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetOnAirTVUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetTopRatedMoviesUseCase
import com.nandaiqbalh.muppi.home_feature.domain.usecase.GetUpcomingMoviesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListMovieViewModel(
	private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
	private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
	private val getOnAirTVUseCase: GetOnAirTVUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(ListMovieState())
	val state: StateFlow<ListMovieState> get() = _state

	fun onAction(action: ListMovieAction) {
		when (action) {

			is ListMovieAction.GetMovieList -> {
				getMovieList(
					page = action.page,
					source = action.source,
					isFirstLoad = action.isFirstLoad
				)
			}

			else -> {}
		}
	}

	private fun getMovieList(page: Int = 1, source: Int = 1, isFirstLoad: Boolean) {
		viewModelScope.launch {

			if (isFirstLoad) {
				updateState { it.copy(moviesState = UiState.Loading) }
			} else {
				updateState { it.copy(nextPageState = UiState.Loading) }
			}

			val uiState = when (source) {
				1 -> {
					if (!isFirstLoad){
						delay(2000)
					}
					getTopRatedMoviesUseCase.execute(page)
				}

				2 -> {
					if (!isFirstLoad){
						delay(2000)
					}
					getUpcomingMoviesUseCase.execute(page)
				}

				3 -> {
					if (!isFirstLoad){
						delay(2000)
					}
					getOnAirTVUseCase.execute(page)
				}

				else -> {
					UiState.Loading
				}
			}

			when (uiState) {
				is UiState.Success -> {

					val previousMovies = state.value.movies

					val newMovies = uiState.data.filterNot { newMovie ->
						previousMovies.any { it.id == newMovie.id }  // Check if the movie id already exists in previousMovies
					}

					// Update the UI state
					updateState { it.copy(moviesState = uiState, movies = previousMovies + newMovies) }

					updateState { it.copy(nextPageState = UiState.Success(false)) }

					updateState { it.copy(page = it.page + 1) }
				}

				else -> {
					// Update the UI state
					updateState { it.copy(moviesState = uiState) }
				}
			}
		}
	}

	fun updateState(update: (ListMovieState) -> ListMovieState) {
		_state.value = update(_state.value)
	}

}