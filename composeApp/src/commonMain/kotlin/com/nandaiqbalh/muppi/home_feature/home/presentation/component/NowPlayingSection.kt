package com.nandaiqbalh.muppi.home_feature.home.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PagerIndicatorRow
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation

@Composable
fun NowPlayingSection(
	modifier: Modifier = Modifier.fillMaxWidth(),
	moviesState: UiState<List<Movie>>,
	onItemClick: (Int) -> Unit,  // To handle item click
) {
	val pagerState = rememberPagerState(
		initialPage = 0,  // The initial page when the pager starts (0 is the first page)
		initialPageOffsetFraction = 0f,  // This is how much the first page is offset (0 means fully aligned)
		pageCount = { if (moviesState is UiState.Success) 5 else 1 }  // The total number of pages (movies list size)
	)

	Column(
		modifier = modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {

		when (moviesState) {
			is UiState.Success -> {
				// HorizontalPager to display movies
				HorizontalPager(
					state = pagerState,
					modifier = Modifier.fillMaxWidth(),
				) { pageIndex ->

					NowPlayingItem(movie = moviesState.data[pageIndex], onItemClick = onItemClick)
				}

			}

			is UiState.Error -> {

				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(350.dp)
				)

			}

			else -> {
				PulseAnimation(
					modifier = Modifier
						.fillMaxWidth()
						.height(350.dp)
				)
			}
		}

		PagerIndicatorRow(
			pagerState = pagerState
		)
	}
}