package com.nandaiqbalh.muppi.core.domain.model

data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isMovie: Boolean = true
)