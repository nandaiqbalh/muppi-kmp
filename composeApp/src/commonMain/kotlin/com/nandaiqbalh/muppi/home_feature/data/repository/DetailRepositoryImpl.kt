package com.nandaiqbalh.muppi.home_feature.data.repository

import com.nandaiqbalh.muppi.MuppiAppSharedConfig
import com.nandaiqbalh.muppi.core.data.dto.CastDetailDto
import com.nandaiqbalh.muppi.core.data.dto.CreditsDto
import com.nandaiqbalh.muppi.core.data.dto.DetailMovieDto
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.dto.VideosDto
import com.nandaiqbalh.muppi.core.data.mapper.toCastDetail
import com.nandaiqbalh.muppi.core.data.mapper.toCasts
import com.nandaiqbalh.muppi.core.data.mapper.toDetailMovie
import com.nandaiqbalh.muppi.core.data.mapper.toMovies
import com.nandaiqbalh.muppi.core.data.mapper.toMoviesByCast
import com.nandaiqbalh.muppi.core.data.mapper.toVideos
import com.nandaiqbalh.muppi.core.data.remote.ApiException
import com.nandaiqbalh.muppi.core.data.remote.toResultFlow
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.model.Video
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.home_feature.domain.repository.DetailRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
	private val httpClient: HttpClient,
) : DetailRepository {

	override suspend fun getDetail(
		id: Int,
		language: String,
		isMovie: Boolean
	): Flow<NetworkResult<DetailMovie>> = toResultFlow {
		val apiRoute = if (isMovie) {
			ApiRoutes.DETAIL_MOVIE.replace("{movie_id}", id.toString())
		} else {
			ApiRoutes.DETAIL_TV.replace("{tv_id}", id.toString())
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			headers.append("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
		}

		if (response.status.isSuccess()) {
			val responseBody: DetailMovieDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toDetailMovie())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getSimilar(
		id: Int,
		language: String,
		isMovie: Boolean
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val apiRoute = if (isMovie){
			ApiRoutes.SIMILAR_MOVIES.replace("{movie_id}", id.toString())
		} else {
			ApiRoutes.SIMILAR_TV.replace("{tv_id}", id.toString())
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
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

	override suspend fun getCasts(
		id: Int,
		language: String,
		isMovie: Boolean
	): Flow<NetworkResult<List<Cast>>> = toResultFlow {
		val apiRoute = if (isMovie) {
			ApiRoutes.CREDITS_BY_MOVIE_ID.replace("{movie_id}", id.toString())
		} else {
			ApiRoutes.CREDITS_BY_TV_ID.replace("{tv_id}", id.toString())
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
		}

		if (response.status.isSuccess()) {
			val responseBody: CreditsDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toCasts())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getVideos(
		id: Int,
		language: String,
		isMovie: Boolean,
	): Flow<NetworkResult<List<Video>>> = toResultFlow {
		val apiRoute = if (isMovie) {
			ApiRoutes.VIDEOS_MOVIE.replace("{movie_id}", id.toString())
		} else {
			ApiRoutes.VIDEOS_TV.replace("{tv_id}", id.toString())
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
		}

		if (response.status.isSuccess()) {
			val responseBody: VideosDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toVideos())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getCastDetail(id: Int): Flow<NetworkResult<CastDetail>> = toResultFlow {
		val apiRoute = ApiRoutes.CAST_DETAIL.replace("{person_id}", id.toString())

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
		}

		if (response.status.isSuccess()) {
			val responseBody: CastDetailDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toCastDetail())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}

	override suspend fun getCombinedCredits(
		personId: Int,
		language: String,
		isMovie: Boolean,
	): Flow<NetworkResult<List<Movie>>>  = toResultFlow {

		val apiRoute = if (isMovie) {
			ApiRoutes.MOVIE_CREDITS.replace("{person_id}", personId.toString())
		} else {
			ApiRoutes.TV_CREDITS.replace("{person_id}", personId.toString())
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
		}

		if (response.status.isSuccess()) {
			val responseBody: MoviesDto = response.body() ?: throw ApiException(
				response.status.value,
				"Response body is null"
			)
			NetworkResult.Success(responseBody.toMoviesByCast())
		} else {
			throw ApiException(
				response.status.value,
				"HTTP ${response.status.value}: ${response.status.description}"
			)
		}
	}
}