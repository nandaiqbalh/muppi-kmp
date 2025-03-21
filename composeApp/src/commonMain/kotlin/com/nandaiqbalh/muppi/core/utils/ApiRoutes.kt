package com.nandaiqbalh.muppi.core.utils

object ApiRoutes {

	private const val BASE_URL = "https://api.themoviedb.org"
	const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

	const val NOW_PLAYING_MOVIES = "$BASE_URL/3/movie/now_playing"
	const val TOP_RATED_MOVIES = "$BASE_URL/3/movie/top_rated"
	const val UPCOMING_MOVIES = "$BASE_URL/3/movie/upcoming"

	const val DETAIL_MOVIE = "$BASE_URL/3/movie/{movie_id}"
	const val CREDITS_BY_MOVIE_ID = "$BASE_URL/3/movie/{movie_id}/credits"
	const val SIMILAR_MOVIES = "$BASE_URL/3/movie/{movie_id}/similar"
	const val VIDEOS_MOVIE = "$BASE_URL/3/movie/{movie_id}/videos"

	const val ON_AIR_TV = "$BASE_URL/3/tv/on_the_air"

	const val DETAIL_TV = "$BASE_URL/3/tv/{tv_id}"
	const val CREDITS_BY_TV_ID = "$BASE_URL/3/tv/{tv_id}/credits"
	const val SIMILAR_TV = "$BASE_URL/3/tv/{tv_id}/similar"
	const val VIDEOS_TV = "$BASE_URL/3/movie/{tv_id}/videos"

}