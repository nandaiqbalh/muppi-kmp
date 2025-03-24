package com.nandaiqbalh.muppi.saved_feature.data.local_database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedMovieDao{

	@Upsert
	suspend fun upsert(movieEntity: MovieEntity)

	@Query("""
        SELECT * FROM MovieEntity 
        WHERE (:isMovie IS NULL OR isMovie = :isMovie)
        AND (:genreIds IS NULL OR genreIds IN (:genreIds))
        AND (:query IS NULL OR title LIKE '%' || :query || '%')
    """)
	fun getMovies(isMovie: Boolean? = null, genreIds: List<Int>? = null, query: String? = null): Flow<List<MovieEntity>>

	@Query("SELECT * FROM MovieEntity WHERE id = :id")
	suspend fun getSavedMovie(id: Int): MovieEntity?

	@Query("DELETE FROM MovieEntity WHERE id = :id")
	suspend fun deleteSavedMovie(id: Int): Int

}