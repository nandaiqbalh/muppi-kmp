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
			is SavedMovieAction.ExploreMovieOrTv -> {
				getSavedMovies()
			}
			is SavedMovieAction.OnClickSearchIcon -> {
				updateState { it.copy(isSearchFieldVisible = action.isVisible) }
			}
			is SavedMovieAction.OnQueryChange -> {
				updateState { it.copy(keyword = action.keyword, genres = emptyList()) }
			}
			is SavedMovieAction.OnSelectGenres -> {
				updateState { it.copy(genres = action.genres, keyword = "") }
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
		isMovie: Boolean? = null, genreIds: List<Int>? = null, query: String? = null,
	) {
		viewModelScope.launch {
			savedMovieUseCase.getMovies(isMovie, genreIds, query).collect{ result ->
				when(result){
					is UiState.Success -> {
						logging { "data movie ${result.data.first()}" }
						updateState { it.copy(movies = result.data, moviesState = UiState.Success(result.data)) }
					}
					else -> {}
				}
			}
		}
	}

	private fun updateState(update: (SavedMovieState) -> SavedMovieState) {
		_state.value = update(_state.value)
	}

}