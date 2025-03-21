package com.nandaiqbalh.muppi.core.domain.model

data class DetailMovie(
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val spokenLanguages: List<SpokenLanguage>,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class SpokenLanguage(
    val englishName: String,
    val iso6391: String,
    val name: String
)
