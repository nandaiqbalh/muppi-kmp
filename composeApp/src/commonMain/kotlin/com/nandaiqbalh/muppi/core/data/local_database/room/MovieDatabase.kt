package com.nandaiqbalh.muppi.core.data.local_database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nandaiqbalh.muppi.core.data.local_database.dao.HomeOfflineMovieDao
import com.nandaiqbalh.muppi.core.data.local_database.model.NowPlayingEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.SeriesOnAirEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.TopRatedEntity
import com.nandaiqbalh.muppi.core.data.local_database.model.UpcomingEntity
import com.nandaiqbalh.muppi.saved_feature.data.local_database.MovieEntity
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDao

@Database(
	entities = [MovieEntity::class, NowPlayingEntity::class, TopRatedEntity::class, UpcomingEntity::class, SeriesOnAirEntity::class],
	version = 1
)
@TypeConverters(
	StringListTypeConverter::class
)
abstract class MovieDatabase : RoomDatabase() {
	abstract val savedMovieDao: SavedMovieDao
	abstract val homeOfflineMovieDao: HomeOfflineMovieDao

	companion object {
		const val DB_NAME = "movie.db"
	}
}