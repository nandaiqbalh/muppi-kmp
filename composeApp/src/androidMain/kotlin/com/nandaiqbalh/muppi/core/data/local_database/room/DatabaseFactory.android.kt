package com.nandaiqbalh.muppi.core.data.local_database.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
	private val context: Context
) {
	actual fun create(): RoomDatabase.Builder<MovieDatabase> {
		val appContext = context.applicationContext

		val dbFile = appContext.getDatabasePath(MovieDatabase.DB_NAME)

		return Room.databaseBuilder(
			context = appContext,
			name = dbFile.absolutePath
		)
	}
}