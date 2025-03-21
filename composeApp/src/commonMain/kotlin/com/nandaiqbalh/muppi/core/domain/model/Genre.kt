package com.nandaiqbalh.muppi.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Genre(val id: Int, val name: String)

// Define the list of all genres
val genreList = listOf(
	Genre(28, "Action"),
	Genre(12, "Adventure"),
	Genre(16, "Animation"),
	Genre(35, "Comedy"),
	Genre(80, "Crime"),
	Genre(99, "Documentary"),
	Genre(18, "Drama"),
	Genre(10751, "Family"),
	Genre(14, "Fantasy"),
	Genre(36, "History"),
	Genre(27, "Horror"),
	Genre(10402, "Music"),
	Genre(9648, "Mystery"),
	Genre(10749, "Romance"),
	Genre(878, "Science Fiction"),
	Genre(10770, "TV Movie"),
	Genre(53, "Thriller"),
	Genre(10752, "War"),
	Genre(37, "Western")
)

// Function to map genre IDs to genre names for a movie
fun getGenreNamesByIds(genreIds: List<Int>): List<String> {
	return genreIds.mapNotNull { genreId ->
		genreList.find { it.id == genreId }?.name
	}
}