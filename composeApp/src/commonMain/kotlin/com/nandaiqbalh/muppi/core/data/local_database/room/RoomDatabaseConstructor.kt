package com.nandaiqbalh.muppi.core.data.local_database.room

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor : RoomDatabaseConstructor<MovieDatabase> {

	override fun initialize(): MovieDatabase

}