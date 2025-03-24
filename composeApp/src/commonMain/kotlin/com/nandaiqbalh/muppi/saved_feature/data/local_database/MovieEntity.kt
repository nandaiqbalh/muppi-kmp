package com.nandaiqbalh.muppi.saved_feature.data.local_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
	val adult: Boolean,
	val backdropPath: String,
	val genreIds: List<Int>,
	@PrimaryKey
	val id: Int,
	val overview: String,
	val posterPath: String,
	val releaseDate: String,
	val title: String,
	val voteAverage: Double,
	val voteCount: Int,
	val isMovie: Boolean,
)