package com.nandaiqbalh.muppi.home_feature.home.presentation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.presentation.utils.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.utils.primaryColor

@Composable
fun NowPlayingSection(
	movies: List<Movie>,
	onItemClick: (Int) -> Unit,  // To handle item click
) {
	// Setting up PagerState for the HorizontalPager
	val pagerState = rememberPagerState(
		initialPage = 0,  // The initial page when the pager starts (0 is the first page)
		initialPageOffsetFraction = 0f,  // This is how much the first page is offset (0 means fully aligned)
		pageCount = { movies.size }  // The total number of pages (movies list size)
	)

	Column(
		modifier = Modifier
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		// HorizontalPager to display movies
		HorizontalPager(
			state = pagerState,
			modifier = Modifier.fillMaxWidth(),
		) { pageIndex ->
			NowPlayingItem(movie = movies[pageIndex], onItemClick = onItemClick)
		}

		// Indicators at the bottom center (reflecting the current page)
		Row(
			modifier = Modifier
				.padding(top = 4.dp)
		) {
			// Loop through the indicators
			for (i in 0 until minOf(5, movies.size)) {
				// Smoothly animate the color of the indicator
				val indicatorColor by animateColorAsState(
					targetValue = if (i == pagerState.currentPage) primaryColor else inactiveColor
				)

				// Smoothly animate the size of the indicator
				val indicatorWidth by animateDpAsState(
					targetValue = if (i == pagerState.currentPage) 24.dp else 8.dp
				)

				Box(
					modifier = Modifier
						.padding(4.dp)
						.width(indicatorWidth)  // Animated width for smooth size change
						.height(8.dp)
						.background(indicatorColor, shape = CircleShape)
				)
			}
		}
	}
}