package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.items.MovieWithTitleItem
import com.nandaiqbalh.muppi.core.presentation.primaryBackground

@Composable
fun SimilarMoviesSection(
	similarMoviesState: UiState<List<Movie>>,
	onClickItem: (Int) -> Unit,
) {

	when (similarMoviesState) {
		is UiState.Success -> {
			val movies = similarMoviesState.data

			LazyRow(
				modifier = Modifier
					.fillMaxWidth(),
				contentPadding = PaddingValues(horizontal = 16.dp)
			) {

				itemsIndexed(movies) { index, movie ->

					MovieWithTitleItem(
						modifier = Modifier
							.clickable {
								onClickItem(movie.id)
							},
						movie = movie,
					)

					// Conditional spacer after each item, except the last one
					if (index != similarMoviesState.data.lastIndex) {
						Spacer(modifier = Modifier.width(16.dp))
					}
				}
			}
		}

		is UiState.Error -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){
				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(250.dp)
				)
			}
		}

		else -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){

				PulseAnimation(
					modifier = Modifier.height(250.dp).fillMaxWidth()
				)
			}
		}
	}
}