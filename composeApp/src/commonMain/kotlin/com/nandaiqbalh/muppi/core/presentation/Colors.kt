package com.nandaiqbalh.muppi.core.presentation

import androidx.compose.ui.graphics.Color

// Extension function to convert hexadecimal color string to Color object
fun String.toColor(): Color {
	require(startsWith("#") && (length == 7 || length == 9)) { "Invalid hex color format" }
	val colorInt = substring(1).toLong(16)
	return if (length == 7) {
		Color(
			red = (colorInt shr 16 and 0xFF) / 255f,
			green = (colorInt shr 8 and 0xFF) / 255f,
			blue = (colorInt and 0xFF) / 255f
		)
	} else {
		Color(
			red = (colorInt shr 16 and 0xFF) / 255f,
			green = (colorInt shr 8 and 0xFF) / 255f,
			blue = (colorInt and 0xFF) / 255f,
			alpha = (colorInt shr 24 and 0xFF) / 255f
		)
	}
}


val primaryBackground = "#202020".toColor()
val primaryColor = "#2497FE".toColor()
val onBackground = "#2A2E31".toColor()
val primaryFont = "#EFF0F9".toColor()
val inactiveColor = "#BCBFDA".toColor()
val primaryColor200 = "#1D6AB0".toColor()
val inactiveDarkColor = "#7C7070".toColor()
val gray50 = "#F5F7FA".toColor()
val gray100 = "#EEF2F6".toColor()
val gray200 = "#E3E8EF".toColor()
val gray300 = "#BFC7D1".toColor()