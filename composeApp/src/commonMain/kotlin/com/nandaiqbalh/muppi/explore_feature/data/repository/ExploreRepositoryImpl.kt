package com.nandaiqbalh.muppi.explore_feature.data.repository

import com.nandaiqbalh.muppi.MuppiAppSharedConfig
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.mapper.toMovies
import com.nandaiqbalh.muppi.core.data.remote.ApiException
import com.nandaiqbalh.muppi.core.data.remote.toResultFlow
import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.explore_feature.domain.repository.ExploreRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

class ExploreRepositoryImpl(
	private val httpClient: HttpClient,
) : ExploreRepository {

	override suspend fun exploreMovieOrTv(
		page: Int,
		language: String,
		isMovie: Boolean,
		genres: String,
		keyword: String,
	): Flow<NetworkResult<List<Movie>>> = toResultFlow {

		val apiRoute = if (isMovie) {
			if (keyword.isNotEmpty()){
				ApiRoutes.SEARCH_MOVIE
			} else {
				ApiRoutes.DISCOVER_MOVIE
			}
		} else {
			if (keyword.isNotEmpty()){
				ApiRoutes.SEARCH_TV
			} else {
				ApiRoutes.DISCOVER_TV
			}
		}

		val response = httpClient.get(apiRoute) {
			contentType(ContentType.Application.Json)
			header("Authorization", "Bearer ${MuppiAppSharedConfig.TMDB_API_KEY}")
			parameter("language", language)
			parameter("page", page)

			if (genres.isNotEmpty()) {
				parameter("with_genres", genres)
			}
			if (keyword.isNotEmpty()){
				parameter("query", keyword)
			}
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
}