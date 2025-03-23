package com.nandaiqbalh.muppi.home_feature.data.repository

import com.nandaiqbalh.muppi.MuppiAppSharedConfig
import com.nandaiqbalh.muppi.core.data.remote.ApiException
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.data.remote.toResultFlow
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.dto.SeriesDto
import com.nandaiqbalh.muppi.core.data.mapper.toMovies
import com.nandaiqbalh.muppi.home_feature.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
	private val httpClient: HttpClient
) : HomeRepository {

	override suspend fun getNowPlayingMovies(
		page: Int,
		language: String,
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val response = httpClient.get(ApiRoutes.NOW_PLAYING_MOVIES) {
			contentType(ContentType.Application.Json)
			headers.append("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("page", page)
			parameter("language",language)
		}

		if (response.status.isSuccess()) {
			val responseBody: MoviesDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toMovies())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getTopRatedMovies(
		page: Int,
		language: String,
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val response = httpClient.get(ApiRoutes.TOP_RATED_MOVIES) {
			contentType(ContentType.Application.Json)
			headers.append("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("page", page)
			parameter("language",language)
		}

		if (response.status.isSuccess()) {
			val responseBody: MoviesDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toMovies())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getUpcomingMovies(
		page: Int,
		language: String,
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val response = httpClient.get(ApiRoutes.UPCOMING_MOVIES) {
			contentType(ContentType.Application.Json)
			headers.append("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("page", page)
			parameter("language",language)
		}

		if (response.status.isSuccess()) {
			val responseBody: MoviesDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toMovies())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getOnAirTV(page: Int, language: String): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val response = httpClient.get(ApiRoutes.ON_AIR_TV) {
			contentType(ContentType.Application.Json)
			headers.append("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("page", page)
			parameter("language",language)
		}

		if (response.status.isSuccess()) {
			val responseBody: SeriesDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toMovies())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}
}