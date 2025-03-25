package com.nandaiqbalh.muppi.core.data.local_database.room

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object StringListTypeConverter{

	@TypeConverter
	fun fromString(value: String): List<Int>{
		return Json.decodeFromString(value)
	}

	@TypeConverter
	fun toString(list: List<Int>): String{
		return Json.encodeToString(list)
	}
}