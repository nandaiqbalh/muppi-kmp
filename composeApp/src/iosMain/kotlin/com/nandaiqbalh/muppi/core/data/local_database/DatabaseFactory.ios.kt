package com.nandaiqbalh.muppi.core.data.local_database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDatabase

actual class DatabaseFactory {
	actual fun create(): RoomDatabase.Builder<SavedMovieDatabase> {

		val dbFile = documentDirectory() + "/${SavedMovieDatabase.DB_NAME}"

		return Room.databaseBuilder<SavedMovieDatabase>(
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