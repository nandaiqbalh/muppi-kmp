package com.nandaiqbalh.muppi.core.utils

object ApiRoutes {

	private const val BASE_URL = "https://api.themoviedb.org"
	const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

	const val NOW_PLAYING_MOVIES = "$BASE_URL/3/movie/now_playing"
	const val TOP_RATED_MOVIES = "$BASE_URL/3/movie/top_rated"
	const val UPCOMING_MOVIES = "$BASE_URL/3/movie/upcoming"

	const val ON_AIR_TV = "$BASE_URL/3/tv/on_the_air"

}