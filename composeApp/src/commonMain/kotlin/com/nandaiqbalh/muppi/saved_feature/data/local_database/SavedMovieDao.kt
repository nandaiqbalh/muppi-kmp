package com.nandaiqbalh.muppi.saved_feature.data.local_database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedMovieDao{

	@Upsert
	suspend fun upsert(movieEntity: MovieEntity)

	@Query("SELECT * FROM MovieEntity")
	fun getSavedMovies(): Flow<List<MovieEntity>>

	@Query("SELECT * FROM MovieEntity WHERE title LIKE '%' || :query || '%'")
	fun searchSavedMovie(query: String): Flow<List<MovieEntity>>

	@Query("SELECT * FROM MovieEntity WHERE isMovie = :isMovie AND (:genreIds IS NULL OR genreIds IN (:genreIds))")
	fun filterMovie(isMovie: Boolean, genreIds: List<Int>?): Flow<List<MovieEntity>>

	@Query("SELECT * FROM MovieEntity WHERE id = :id")
	suspend fun getSavedMovie(id: String): MovieEntity?

	@Query("DELETE FROM MovieEntity WHERE id = :id")
	suspend fun deleteSavedMovie(id: String)

}