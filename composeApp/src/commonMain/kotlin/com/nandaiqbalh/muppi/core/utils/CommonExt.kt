package com.nandaiqbalh.muppi.core.utils


import kotlinx.datetime.LocalDate

fun String.toFormattedDate(): String {
	return try {
		// Parse the string into LocalDate using kotlinx-datetime
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

		// Return the formatted date string: "15 July 2025"
		"$day $month $year"
	} catch (e: Exception) {
		// If there was an error in parsing, return a default message
		"Invalid date format"
	}
}
