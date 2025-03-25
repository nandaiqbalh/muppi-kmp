package com.nandaiqbalh.muppi.core.data.local_database.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaiqbalh.muppi.core.data.local_database.room.MovieDatabase

actual class DatabaseFactory {
	actual fun create(): RoomDatabase.Builder<MovieDatabase> {

		val dbFile = documentDirectory() + "/${MovieDatabase.DB_NAME}"

		return Room.databaseBuilder<MovieDatabase>(
			name = dbFile
		)

	}

	private fun documentDirectory(): String {
		val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
			directory = NSDocumentDirectory,
			inDomain = NSUserDomainMask,
			appropiateForURL = null,
			create = false,
			error = null
		)

		return requireNotNull(documentDirectory?.path)
	}
}