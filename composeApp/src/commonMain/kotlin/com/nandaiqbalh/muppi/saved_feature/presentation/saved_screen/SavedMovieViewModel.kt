package com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.utils.logging
import com.nandaiqbalh.muppi.saved_feature.domain.usecase.SavedMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedMovieViewModel(
	private val savedMovieUseCase: SavedMovieUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(SavedMovieState())
	val state: StateFlow<SavedMovieState> get() = _state

	fun onAction(action: SavedMovieAction) {
		when (action) {
			is SavedMovieAction.GetSavedMovies -> {
				getSavedMovies(
					isMovie = action.isMovie,
					query = action.query
				)
			}
			is SavedMovieAction.OnClickSearchIcon -> {
				updateState { it.copy(isSearchFieldVisible = action.isVisible) }
			}
			is SavedMovieAction.OnQueryChange -> {
				updateState { it.copy(keyword = action.keyword) }
			}
			is SavedMovieAction.OnSelectType -> {
				updateState { it.copy(isMovie = action.isMovie) }
			}
			is SavedMovieAction.OnReachBottom -> {
				updateState { it.copy(page = it.page + 1) }
			}

			else -> {}
		}
	}

	private fun getSavedMovies(
		isMovie: Boolean? = null, query: String? = null,
	) {
		viewModelScope.launch {

			updateState { it.copy(moviesState = UiState.Loading) }

			val uiState = savedMovieUseCase.getMovies(isMovie, query)

			when (uiState) {
				is UiState.Success -> {
					updateState {
						it.copy(
							movies = uiState.data,
							moviesState = UiState.Success(uiState.data)
						)
					}
				}

				else -> {

				}
			}

			updateState { it.copy(moviesState = uiState) }
		}
	}

	private fun updateState(update: (SavedMovieState) -> SavedMovieState) {
		_state.value = update(_state.value)
	}

}