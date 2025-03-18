package com.nandaiqbalh.muppi.home_feature.home.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.domain.model.Movie

@Composable
fun UpComingMoviesSection(
	movies: List<Movie>,
	onItemClick: (Int) -> Unit,  // To handle item click
) {
	LazyRow(
		modifier = Modifier
			.fillMaxWidth(),
		contentPadding = PaddingValues(horizontal = 16.dp)
	) {
		itemsIndexed(movies) { index, movie ->

			UpcomingMoviesItem(
				movie = movie,
				onItemClick = { id ->
					onItemClick(id)
				}
			)

			// Conditional spacer after each item, except the last one
			if (index != movies.lastIndex) {
				Spacer(modifier = Modifier.width(16.dp))
			}
		}

	}
}