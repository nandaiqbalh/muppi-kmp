package com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation

@Composable
fun UpComingMoviesSection(
	moviesState: UiState<List<Movie>>,
	onItemClick: (Int) -> Unit,  // To handle item click
) {

	when (moviesState) {
		is UiState.Success -> {
			LazyRow(
				modifier = Modifier
					.fillMaxWidth(),
				contentPadding = PaddingValues(horizontal = 16.dp)
			) {
				itemsIndexed(moviesState.data) { index, movie ->

					UpcomingMoviesItem(
						movie = movie,
						onItemClick = { id ->
							onItemClick(id)
						}
					)

					// Conditional spacer after each item, except the last one
					if (index != moviesState.data.lastIndex) {
						Spacer(modifier = Modifier.width(16.dp))
					}
				}

			}
		}

		is UiState.Error -> {
			ErrorComponent(
				modifier = Modifier.fillMaxWidth().height(270.dp)
			)
		}

		else -> {

			PulseAnimation(
				modifier = Modifier
					.fillMaxWidth()
					.height(185.dp)
			)
		}
	}
}