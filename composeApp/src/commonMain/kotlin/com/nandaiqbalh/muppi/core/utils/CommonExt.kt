package com.nandaiqbalh.muppi.core.utils


import kotlinx.datetime.LocalDate


fun String.toFormattedDate(): String {
	return try {
		// Parse the string into LocalDate using kotlinx-datetime (ISO format: YYYY-MM-DD)
		val date = LocalDate.parse(this)

		// Define the month names manually
		val monthNames = listOf(
			"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"
		)

		// Get the day, month name, and year from the LocalDate
		val day = date.dayOfMonth
		val month = monthNames[date.monthNumber - 1]  // Adjust for 1-based indexing
		val year = date.year

		// Return the formatted date string: "22 January 2025"
		"$day $month $year"
	} catch (e: Exception) {
		// If there was an error in parsing, return a default message
		"Invalid date format"
	}
}

private const val BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

fun Int?.orZero() = this ?: 0

fun Int?.orNull(): Int? {
	return if (this == 0) null else this
}

fun Int?.orOne() = this ?: 1

fun Long?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Boolean.toInt(): Int = if (this) 1 else 0

fun Int.isZero(): Boolean = this <= 0

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true

fun Int.formatDigit(digitCount: Int): String{
	return this.toString().padStart(digitCount, '0')
}

fun String.capitalizeFirstChar() = this.lowercase().replaceFirstChar(Char::uppercase)

fun String.toDoublePair(): Pair<Double, Double> {
	val parts = this.split(",")
	return if (parts.size == 2) {
		try {
			val first = parts[0].toDouble()
			val second = parts[1].toDouble()
			Pair(first, second)
		} catch (e: NumberFormatException) {
			e.printStackTrace()
			Pair(0.0, 0.0)
		}
	} else {
		Pair(0.0, 0.0)
	}
}
