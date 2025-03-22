package com.nandaiqbalh.muppi.core.data.mapper

import com.nandaiqbalh.muppi.core.data.dto.DetailMovieDto
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.utils.orFalse
import com.nandaiqbalh.muppi.core.utils.orZero
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.dto.SeriesDto
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie

// Extension function to map MoviesDto to a list of Movie objects
fun MoviesDto.toMovies(): List<Movie> {
	return this.results?.map { movieDto ->
		// Mapping each Result (movieDto) to a Movie using the provided extension functions
		Movie(
			adult = movieDto.adult.orFalse(),
			backdropPath = movieDto.backdropPath.orEmpty(),
			genreIds = movieDto.genreIds.orEmpty(),
			id = movieDto.id.orZero(),
			originalLanguage = movieDto.originalLanguage.orEmpty(),
			originalTitle = if (movieDto.originalTitle.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.originalTitle,
			overview = movieDto.overview.orEmpty(),
			popularity = movieDto.popularity.orZero(),
			posterPath = movieDto.posterPath.orEmpty(),
			releaseDate = movieDto.releaseDate.orEmpty(),
			title = if (movieDto.title.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.title,
			video = movieDto.video.orFalse(),
			voteAverage = movieDto.voteAverage.orZero(),
			voteCount = movieDto.voteCount.orZero()
		)
	} ?: emptyList() // Return an empty list if results is null
}

fun MoviesDto.toMoviesByCast(): List<Movie> {
	return this.cast?.map { movieDto ->
		// Mapping each Result (movieDto) to a Movie using the provided extension functions
		Movie(
			adult = movieDto.adult.orFalse(),
			backdropPath = movieDto.backdropPath.orEmpty(),
			genreIds = movieDto.genreIds.orEmpty(),
			id = movieDto.id.orZero(),
			originalLanguage = movieDto.originalLanguage.orEmpty(),
			originalTitle = if (movieDto.originalTitle.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.originalTitle,
			overview = movieDto.overview.orEmpty(),
			popularity = movieDto.popularity.orZero(),
			posterPath = movieDto.posterPath.orEmpty(),
			releaseDate = movieDto.releaseDate.orEmpty(),
			title = if (movieDto.title.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.title,
			video = movieDto.video.orFalse(),
			voteAverage = movieDto.voteAverage.orZero(),
			voteCount = movieDto.voteCount.orZero()
		)
	} ?: emptyList() // Return an empty list if results is null
}

fun DetailMovieDto.toDetailMovie(): DetailMovie {
	return DetailMovie(
		adult = this.adult.orFalse(),
		backdropPath = this.backdropPath.orEmpty(),
		id = this.id.orZero(),
		originalTitle = this.originalTitle.orEmpty(),
		overview = this.overview.orEmpty(),
		posterPath = this.posterPath.orEmpty(),
		releaseDate = if (this.releaseDate.isNullOrEmpty()) this.first_air_date.orEmpty() else this.releaseDate,
		title = this.title.orEmpty(),
		video = this.video.orFalse(),
		voteAverage = this.voteAverage.orZero(),
		voteCount = this.voteCount.orZero(),
		genres = this.genres,
	)
}

fun SeriesDto.toMovies(): List<Movie> {
	return this.results?.map { resultSeries ->
		Movie(
			adult = resultSeries.adult.orFalse(),
			id = resultSeries.id.orZero(), 
			title = resultSeries.name.orEmpty(),
			overview = resultSeries.overview.orEmpty(),
			backdropPath = resultSeries.backdropPath.orEmpty(),
			releaseDate = resultSeries.firstAirDate.orEmpty(),
			popularity = resultSeries.popularity.orZero(),
			posterPath = resultSeries.posterPath.orEmpty(),
			voteAverage = resultSeries.voteAverage.orZero(),
			voteCount = resultSeries.voteCount.orZero(),
			originalLanguage = resultSeries.originalLanguage.orEmpty(),
			originalTitle = resultSeries.originalName.orEmpty(),
			genreIds = resultSeries.genreIds.orEmpty(),
			video =  false
		)
	} ?: emptyList() // If results is null, return an empty list
}

