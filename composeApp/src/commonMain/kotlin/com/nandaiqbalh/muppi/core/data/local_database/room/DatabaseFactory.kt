package com.nandaiqbalh.muppi.core.data.local_database.room

import androidx.room.RoomDatabase

expect class DatabaseFactory {
	fun create(): RoomDatabase.Builder<MovieDatabase>
}