package com.nandaiqbalh.muppi.core.data.remote

import com.nandaiqbalh.muppi.core.domain.ApiError
import com.nandaiqbalh.muppi.core.utils.emptyString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class ApiException(
    @Transient
    val httpCode: Int = 0,
    @SerialName("message")
    val errorMessage: String
): Exception() {
    fun map(isError: Boolean, message: String): ApiError {
        return ApiError(
            httpCode = httpCode,
            isError = isError,
            errorMessage = message,
            errorCode = emptyString()
        )
    }
}