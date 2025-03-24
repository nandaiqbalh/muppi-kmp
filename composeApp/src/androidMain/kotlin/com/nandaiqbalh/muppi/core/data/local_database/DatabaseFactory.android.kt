package com.nandaiqbalh.muppi.core.data.local_database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDatabase

actual class DatabaseFactory(
	private val context: Context
) {
	actual fun create(): RoomDatabase.Builder<SavedMovieDatabase> {
		val appContext = context.applicationContext

		val dbFile = appContext.getDatabasePath(SavedMovieDatabase.DB_NAME)

		return Room.databaseBuilder(
			context = appContext,
			name = dbFile.absolutePath
		)
	}
}