package com.nandaiqbalh.muppi.core.data.local_database

import androidx.room.RoomDatabase
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDatabase

expect class DatabaseFactory {
	fun create(): RoomDatabase.Builder<SavedMovieDatabase>
}