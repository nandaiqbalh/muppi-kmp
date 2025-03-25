package com.nandaiqbalh.muppi.core.data.local_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.nandaiqbalh.muppi.core.data.local_database.model.NowPlayingEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.UpcomingEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.TopRatedEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.SeriesOnAirEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeOfflineMovieDao {

	// --- Now Playing Movies ---
	@Upsert
	suspend fun upsertNowPlayingMovie(movieEntity: NowPlayingEntity)

	@Query("""
        SELECT * FROM NowPlayingEntity 
        WHERE (:isMovie IS NULL OR isMovie = :isMovie)
        AND (:query IS NULL OR title LIKE '%' || :query || '%')
    """)
	fun getNowPlayingMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<NowPlayingEntity>>

	@Query("SELECT * FROM NowPlayingEntity WHERE id = :id")
	suspend fun getNowPlayingDetailMovie(id: Int): NowPlayingEntity?

	@Query("DELETE FROM NowPlayingEntity WHERE id = :id")
	suspend fun deleteNowPlayingMovie(id: Int): Int

	// --- Upcoming Movies ---
	@Upsert
	suspend fun upsertUpcomingMovie(movieEntity: UpcomingEntity)

	@Query("""
        SELECT * FROM UpcomingEntity 
        WHERE (:isMovie IS NULL OR isMovie = :isMovie)
        AND (:query IS NULL OR title LIKE '%' || :query || '%')
    """)
	fun getUpcomingMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<UpcomingEntity>>

	@Query("SELECT * FROM UpcomingEntity WHERE id = :id")
	suspend fun getUpcomingDetailMovie(id: Int): UpcomingEntity?

	@Query("DELETE FROM UpcomingEntity WHERE id = :id")
	suspend fun deleteUpcomingMovie(id: Int): Int

	// --- Top Rated Movies ---
	@Upsert
	suspend fun upsertTopRatedMovie(movieEntity: TopRatedEntity)

	@Query("""
        SELECT * FROM TopRatedEntity 
        WHERE (:isMovie IS NULL OR isMovie = :isMovie)
        AND (:query IS NULL OR title LIKE '%' || :query || '%')
    """)
	fun getTopRatedMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<TopRatedEntity>>

	@Query("SELECT * FROM TopRatedEntity WHERE id = :id")
	suspend fun getTopRatedDetailMovie(id: Int): TopRatedEntity?

	@Query("DELETE FROM TopRatedEntity WHERE id = :id")
	suspend fun deleteTopRatedMovie(id: Int): Int

	// --- Series On Air Movies ---
	@Upsert
	suspend fun upsertSeriesOnAirMovie(movieEntity: SeriesOnAirEntity)

	@Query("""
        SELECT * FROM SeriesOnAirEntity 
        WHERE (:isMovie IS NULL OR isMovie = :isMovie)
        AND (:query IS NULL OR title LIKE '%' || :query || '%')
    """)
	fun getSeriesOnAirMovies(isMovie: Boolean? = null, query: String? = null): Flow<List<SeriesOnAirEntity>>

	@Query("SELECT * FROM SeriesOnAirEntity WHERE id = :id")
	suspend fun getSeriesOnAirDetailMovie(id: Int): SeriesOnAirEntity?

	@Query("DELETE FROM SeriesOnAirEntity WHERE id = :id")
	suspend fun deleteSeriesOnAirMovie(id: Int): Int
}
