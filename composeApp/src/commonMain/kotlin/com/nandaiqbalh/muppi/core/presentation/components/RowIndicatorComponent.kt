package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.primaryColor

@Composable
fun PagerIndicatorRow(
	pagerState: PagerState,
	activeColor: Color = primaryColor,  // Default active color: Purple
	iinactiveColor: Color = inactiveColor,  // Default inactive color: Grey
	activeIndicatorSize: Dp = 24.dp,  // Default active indicator size
	inactiveIndicatorSize: Dp = 8.dp,  // Default inactive indicator size
) {
	Row(
		modifier = Modifier.padding(top = 4.dp)
	) {
		// Loop through the total number of indicators
		for (i in 0 until pagerState.pageCount) {
			// Animate the color of the indicator
			val indicatorColor by animateColorAsState(
				targetValue = if (i == pagerState.currentPage) activeColor else iinactiveColor
			)

			// Animate the size of the indicator
			val indicatorWidth by animateDpAsState(
				targetValue = if (i == pagerState.currentPage) activeIndicatorSize else inactiveIndicatorSize
			)

			Box(
				modifier = Modifier
					.padding(4.dp)
					.width(indicatorWidth)  // Animated width for smooth size change
					.height(8.dp)  // Fixed height for all indicators
					.background(indicatorColor, shape = CircleShape)
			)
		}
	}
}
