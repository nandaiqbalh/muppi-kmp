package com.nandaiqbalh.muppi.core.data.dto

import com.nandaiqbalh.muppi.core.domain.model.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailMovieDto(
    @SerialName("adult")
    val adult: Boolean? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genres")
    val genres: List<Genre> = emptyList(),

    @SerialName("homepage")
    val homepage: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    val original_name: String? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    val first_air_date: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("video")
    val video: Boolean? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("vote_count")
    val voteCount: Int? = null
)
