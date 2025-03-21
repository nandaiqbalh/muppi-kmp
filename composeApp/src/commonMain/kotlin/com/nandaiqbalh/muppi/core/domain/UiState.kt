package com.nandaiqbalh.muppi.core.domain

sealed class UiState<out T> {

    data object Initial : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<T>(val errorMessage: String, val data: T? = null) : UiState<T>()
}
