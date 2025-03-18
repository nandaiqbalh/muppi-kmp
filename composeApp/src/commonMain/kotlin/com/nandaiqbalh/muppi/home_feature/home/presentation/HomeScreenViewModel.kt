package com.nandaiqbalh.muppi.home_feature.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel : ViewModel() {

	private val _state = MutableStateFlow(HomeScreenState())
	val state: StateFlow<HomeScreenState> get() = _state

	fun onAction(action: HomeScreenAction) {

		when(action){
			is HomeScreenAction.OnClickItem -> {

			}
			else -> {}
		}
	}

}