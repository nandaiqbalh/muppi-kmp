package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.explore_feature.domain.usecase.ExploreMovieOrTvUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExploreViewModel(
	private val exploreMovieOrTvUseCase: ExploreMovieOrTvUseCase,
) : ViewModel() {

	private val _state = MutableStateFlow(ExploreState())
	val state: StateFlow<ExploreState> get() = _state

	fun onAction(action: ExploreAction) {
		when (action) {
			is ExploreAction.ExploreMovieOrTv -> {
				exploreMovieOrTv(
					isFirstLoad = action.isFirstLoad
				)
			}
			is ExploreAction.OnClickSearchIcon -> {
				updateState { it.copy(isSearchFieldVisible = action.isVisible) }
			}
			is ExploreAction.OnQueryChange -> {
				updateState { it.copy(keyword = action.keyword, genres = emptyList()) }
			}
			is ExploreAction.OnSelectGenres -> {
				updateState { it.copy(genres = action.genres, keyword = "") }
			}
			is ExploreAction.OnSelectType -> {
				updateState { it.copy(isMovie = action.isMovie) }
			}
			is ExploreAction.OnReachBottom -> {
				updateState { it.copy(page = it.page + 1) }
			}

			else -> {}
		}
	}

	private fun exploreMovieOrTv(
		page: Int = state.value.page,
		genres: List<Int> = state.value.genres,
		isMovie: Boolean = state.value.isMovie,
		language: String = "en-US",
		keyword: String = state.value.keyword,
		isFirstLoad: Boolean,
	) {
		viewModelScope.launch {

			if (isFirstLoad) {
				updateState { it.copy(moviesState = UiState.Loading, movies = emptyList(), page = 1) }
			} else {
				updateState { it.copy(nextPageState = UiState.Loading) }
			}

			val stringGenres = genres.joinToString("|")

			when (val uiState = exploreMovieOrTvUseCase.execute(page, isMovie, language, stringGenres, keyword)) {
				is UiState.Success -> {

					val previousMovies = state.value.movies

					// Update the UI state
					updateState {
						it.copy(
							moviesState = uiState,
							movies = previousMovies + uiState.data
						)
					}

					updateState { it.copy(nextPageState = UiState.Success(false)) }

				}

				else -> {
					// Update the UI state
					updateState { it.copy(moviesState = uiState) }
				}
			}
		}
	}


	private fun updateState(update: (ExploreState) -> ExploreState) {
		_state.value = update(_state.value)
	}

}