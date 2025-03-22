package com.nandaiqbalh.muppi.home_feature.detail_movie.domain.repository

import com.nandaiqbalh.muppi.core.domain.NetworkResult
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

	suspend fun getDetail(id: Int, language: String = "en-US", isMovie: Boolean): Flow<NetworkResult<DetailMovie>>

	suspend fun getSimilar(id: Int, language: String = "en-US", isMovie: Boolean): Flow<NetworkResult<List<Movie>>>

	suspend fun getCasts(id: Int, language: String = "en-US", isMovie: Boolean): Flow<NetworkResult<List<Cast>>>

	suspend fun getVideos(id: Int, language: String = "en-US", isMovie: Boolean): Flow<NetworkResult<List<Video>>>

	suspend fun getCastDetail(id: Int): Flow<NetworkResult<CastDetail>>

	suspend fun getCombinedCredits(personId: Int, language: String = "en-US", isMovie: Boolean): Flow<NetworkResult<List<Movie>>>

}