package com.nandaiqbalh.muppi.core.data.mapper

import com.nandaiqbalh.muppi.core.data.dto.DetailMovieDto
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.utils.orFalse
import com.nandaiqbalh.muppi.core.utils.orZero
import com.nandaiqbalh.muppi.core.data.dto.MoviesDto
import com.nandaiqbalh.muppi.core.data.dto.SeriesDto
import com.nandaiqbalh.muppi.core.domain.model.DetailMovie
import com.nandaiqbalh.muppi.core.domain.model.Genre
import com.nandaiqbalh.muppi.core.domain.model.genreList
import com.nandaiqbalh.muppi.saved_feature.data.local_database.MovieEntity

// Extension function to map MoviesDto to a list of Movie objects
fun MoviesDto.toMovies(): List<Movie> {
	return this.results?.map { movieDto ->
		// Mapping each Result (movieDto) to a Movie using the provided extension functions
		Movie(
			adult = movieDto.adult.orFalse(),
			backdropPath = movieDto.backdropPath.orEmpty(),
			genreIds = movieDto.genreIds.orEmpty(),
			id = movieDto.id.orZero(),
			overview = movieDto.overview.orEmpty(),
			posterPath = movieDto.posterPath.orEmpty(),
			releaseDate = movieDto.releaseDate.orEmpty(),
			title = if (movieDto.title.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.title,
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
			overview = movieDto.overview.orEmpty(),
			posterPath = movieDto.posterPath.orEmpty(),
			releaseDate = movieDto.releaseDate.orEmpty(),
			title = if (movieDto.title.isNullOrEmpty()) movieDto.original_name.orEmpty() else movieDto.title,
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
		originalTitle = if (this.originalTitle.isNullOrEmpty()) this.original_name.orEmpty() else this.originalTitle,
		overview = this.overview.orEmpty(),
		posterPath = this.posterPath.orEmpty(),
		releaseDate = if (this.releaseDate.isNullOrEmpty()) this.first_air_date.orEmpty() else this.releaseDate,
		title = if (this.title.isNullOrEmpty()) this.original_name.orEmpty() else this.title,
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
			title = resultSeries.originalName.orEmpty(),
			overview = resultSeries.overview.orEmpty(),
			backdropPath = resultSeries.backdropPath.orEmpty(),
			releaseDate = resultSeries.firstAirDate.orEmpty(),
			posterPath = resultSeries.posterPath.orEmpty(),
			voteAverage = resultSeries.voteAverage.orZero(),
			voteCount = resultSeries.voteCount.orZero(),
			genreIds = resultSeries.genreIds.orEmpty(),
		)
	} ?: emptyList() // If results is null, return an empty list
}

fun Movie.toMovieEntity(): MovieEntity {
	return MovieEntity(
		adult = this.adult.orFalse(),
		backdropPath = this.backdropPath,
		genreIds = this.genreIds,
		id = this.id.orZero(),
		overview = this.overview,
		posterPath = this.posterPath,
		releaseDate = this.releaseDate,
		title = this.title,
		voteAverage = this.voteAverage.orZero(),
		voteCount = this.voteCount.orZero(),
		isMovie = this.isMovie
	)
}

fun MovieEntity.toMovie(): Movie {
	return Movie(
		adult = this.adult.orFalse(),
		backdropPath = this.backdropPath,
		genreIds = this.genreIds,
		id = this.id.orZero(),
		overview = this.overview,
		posterPath = this.posterPath,
		releaseDate = this.releaseDate,
		title = this.title,
		voteAverage = this.voteAverage.orZero(),
		voteCount = this.voteCount.orZero(),
		isMovie = this.isMovie,
	)
}

fun DetailMovie.toMovie(): Movie {
	// Map the genres list to only genre IDs
	val genreIds = genres.map { it.id }  // Map the Genre objects to their ids

	return Movie(
		adult = this.adult,
		backdropPath = this.backdropPath,
		genreIds = genreIds,  // Pass the list of genre IDs
		id = this.id,
		overview = this.overview,
		posterPath = this.posterPath,
		releaseDate = this.releaseDate,
		title = this.title,
		voteAverage = this.voteAverage,
		voteCount = this.voteCount
	)
}

fun Movie.toDetailMovie(): DetailMovie {
	// Map the genreIds list to Genre objects
	val genres = genreIds.mapNotNull { genreId ->
		genreList.find { it.id == genreId }  // Find the Genre object by its ID
	}  // Remove nulls in case an invalid ID was passed

	return DetailMovie(
		adult = this.adult,
		backdropPath = this.backdropPath,
		genres = genres,  // Pass the list of Genre objects
		id = this.id,
		overview = this.overview,
		posterPath = this.posterPath,
		releaseDate = this.releaseDate,
		title = this.title,
		voteAverage = this.voteAverage,
		voteCount = this.voteCount,
		video = false,
		originalTitle = this.title
	)
}
