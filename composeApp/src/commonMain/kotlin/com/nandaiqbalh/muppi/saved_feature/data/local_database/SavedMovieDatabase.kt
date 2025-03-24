package com.nandaiqbalh.muppi.saved_feature.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nandaiqbalh.muppi.core.data.local_database.StringListTypeConverter

@Database(
	entities = [MovieEntity::class],
	version = 1
)
@TypeConverters(
	StringListTypeConverter::class
)
abstract class SavedMovieDatabase : RoomDatabase() {
	abstract val savedMovieDao: SavedMovieDao

	companion object {
		const val DB_NAME = "movie.db"
	}
}