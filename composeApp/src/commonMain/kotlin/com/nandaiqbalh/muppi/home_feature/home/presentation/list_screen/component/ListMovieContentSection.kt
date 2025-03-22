package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.LoadingNextPageComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.items.MovieListItem
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen.ListMovieState
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_error_empty
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListMovieContentSection(
	modifier: Modifier = Modifier,
	lazyListState: LazyListState,
	state: ListMovieState,
	onClickItem: (Int) -> Unit,
) {

	LazyColumn(
		state = lazyListState,
		modifier = modifier,
		contentPadding = PaddingValues(16.dp)
	) {

		if (state.moviesState is UiState.Error) {
			item {
				Box(
					modifier = Modifier
						.background(primaryBackground),
					contentAlignment = Alignment.Center
				) {
					ErrorComponent(
						modifier = Modifier.fillMaxSize()
					)
				}
			}
		}

		if (state.moviesState == UiState.Loading) {
			item {
				Box(
					modifier = Modifier
						.background(primaryBackground),
					contentAlignment = Alignment.Center
				) {

					PulseAnimation(
						modifier = Modifier.fillMaxSize()
					)
				}
			}
		}

		if (state.movies.isEmpty() && state.moviesState !is UiState.Loading) {
			item {
				Text(
					text = stringResource(Res.string.tv_error_empty),
					modifier = Modifier.padding(16.dp),
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_regular)),
						fontSize = 10.sp,
						color = primaryFont,
						textAlign = TextAlign.Start
					)
				)
			}
		} else if (state.moviesState is UiState.Success) {

			itemsIndexed(state.movies) { index, movie ->

				MovieListItem(
					modifier = Modifier
						.clickable {
							onClickItem(movie.id)
						},
					movie = movie,
				)

				// Conditional spacer after each item, except the last one
				if (index != state.movies.lastIndex) {
					Spacer(modifier = Modifier.height(16.dp))
				}
			}
		}

		if (state.nextPageState is UiState.Loading){

			item {
				LoadingNextPageComponent()
			}

			item {
				Spacer(
					modifier = Modifier.height(16.dp)
				)
			}
		}
	}
}
