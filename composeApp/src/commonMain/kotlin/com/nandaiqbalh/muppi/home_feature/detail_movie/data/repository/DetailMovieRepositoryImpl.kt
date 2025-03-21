package com.nandaiqbalh.muppi.home_feature.detail_movie.data.repository

import com.nandaiqbalh.muppi.MuppiAppSharedConfig
import com.nandaiqbalh.muppi.core.data.dto.CreditsDto
import com.nandaiqbalh.muppi.core.data.dto.DetailMovieDto
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.mapper.toCasts
import com.nandaiqbalh.muppi.core.data.mapper.toDetailMovie
import com.nandaiqbalh.muppi.core.data.mapper.toMovies
import com.nandaiqbalh.muppi.core.data.remote.ApiException
import com.nandaiqbalh.muppi.core.data.remote.toResultFlow
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository.DetailMovieRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

class DetailMovieRepositoryImpl(
	private val httpClient: HttpClient,
) : DetailMovieRepository {

	override suspend fun getDetailMovie(
		movieId: Int,
		language: String,
	): Flow<NetworkResult<DetailMovie>> = toResultFlow {
		val apiRoute = ApiRoutes.DETAIL_MOVIE.replace("{movie_id}", movieId.toString())

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

	override suspend fun getSimilarMovies(
		movieId: Int,
		language: String,
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {
		val apiRoute = ApiRoutes.SIMILAR_MOVIES.replace("{movie_id}", movieId.toString())

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

	override suspend fun getMovieCasts(
		movieId: Int,
		language: String,
	): Flow<NetworkResult<List<Cast>>> = toResultFlow {
		val apiRoute = ApiRoutes.CREDITS_BY_MOVIE_ID.replace("{movie_id}", movieId.toString())

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
}