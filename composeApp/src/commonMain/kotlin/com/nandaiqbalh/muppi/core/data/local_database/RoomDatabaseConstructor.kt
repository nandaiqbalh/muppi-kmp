package com.nandaiqbalh.muppi.core.data.local_database

import androidx.room.RoomDatabaseConstructor
import com.nandaiqbalh.muppi.saved_feature.data.local_database.SavedMovieDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<SavedMovieDatabase> {

	override fun initialize(): SavedMovieDatabase

}