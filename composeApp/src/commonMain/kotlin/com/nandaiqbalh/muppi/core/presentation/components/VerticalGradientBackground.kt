package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalGradientBackground(
	height: Int = 86,
	modifier: Modifier
) {
	val gradient = Brush.verticalGradient(
		colors = listOf(
			Color.Transparent, // Top color with transparency (0%)
			Color(0x881C1C27),  // Top color (0%)
			Color(0x881C1C27), // Middle color (15%)
			Color(0xFF202020), // Bottom color (100%)
		),
		startY = 0f,  // Gradient starts from the top
		endY = Float.POSITIVE_INFINITY // Gradient goes to the bottom
	)

	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(height.dp)
			.background(gradient)
	)
}