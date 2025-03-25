package com.nandaiqbalh.muppi.core.data.local_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UpcomingEntity(
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