package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.cast_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetCastDetailUseCase
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.usecase.GetCombinedCreditsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CastDetailViewModel(
	private val getCastDetailUseCase: GetCastDetailUseCase,
	private val getCombinedCreditsUseCase: GetCombinedCreditsUseCase
) : ViewModel() {

	private val _state = MutableStateFlow(CastDetailState())
	val state: StateFlow<CastDetailState> get() = _state

	fun onAction(action: CastDetailAction) {
		when (action) {
			is CastDetailAction.OnClickBack -> {

			}
			else -> {}
		}
	}

	fun getDetailCast(personId: Int) {
		viewModelScope.launch {
			updateState { it.copy(castDetailState = UiState.Loading) }

			// Execute the use case
			val uiState = getCastDetailUseCase.execute(personId)

			// Update the UI state
			updateState { it.copy(castDetailState = uiState) }

		}
	}

	fun getCombinedList(personId: Int) {
		viewModelScope.launch {
			updateState { it.copy(moviesState = UiState.Loading) }

			// Execute the use case
			val uiState = getCombinedCreditsUseCase.execute(personId)

			// Update the UI state
			updateState { it.copy(moviesState = uiState) }

		}
	}


	private fun updateState(update: (CastDetailState) -> CastDetailState) {
		_state.value = update(_state.value)
	}

}