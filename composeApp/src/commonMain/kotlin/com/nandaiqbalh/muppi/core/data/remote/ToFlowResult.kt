package com.nandaiqbalh.muppi.core.data.remote

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> toResultFlow(
	dispatcher: CoroutineDispatcher = Dispatchers.IO,
	call: suspend () -> NetworkResult.Success<T>
) : Flow<NetworkResult<T>> {
	return flow {
		emit(NetworkResult.Loading)
		try {
			val response = call()
			emit(NetworkResult.Success(response.data))
		} catch (e: kotlinx.io.IOException) {
			e.printStackTrace()
			emit(
				NetworkResult.Error(
					message = "Sepertinya koneksi internet milikmu terputus. Pastikan perangkatmu terhubung ke internet dan coba lagi",
					exception = e
				)
			)
		} catch (e: ClientRequestException) {
			// Handle client request exceptions like 404
			e.printStackTrace()
			emit(
				NetworkResult.Error(
					message = "Client error: ${e.response.status.description}",
					exception = e
				)
			)
		} catch (e: ServerResponseException) {
			// Handle server response exceptions like 5xx errors
			e.printStackTrace()
			emit(
				NetworkResult.Error(
					message = "Server error: ${e.response.status.description}",
					exception = e
				)
			)
		} catch (e: ApiException) {
			e.printStackTrace()
			emit(NetworkResult.Error(message = e.errorMessage, exception = e))
		} catch (e: Exception) {
			// Handle any other exceptions
			e.printStackTrace()
			emit(
				NetworkResult.Error(
					message = "An unexpected error occurred: ${e.message}",
					exception = e
				)
			)
		}
	}.flowOn(dispatcher)
}