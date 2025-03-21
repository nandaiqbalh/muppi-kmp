package com.nandaiqbalh.muppi.core.domain

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val exception: Exception) : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()
}

