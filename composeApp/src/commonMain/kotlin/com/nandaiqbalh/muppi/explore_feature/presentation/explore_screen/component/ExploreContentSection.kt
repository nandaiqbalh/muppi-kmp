package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.items.MovieListItem
import com.nandaiqbalh.muppi.core.presentation.inactiveDarkColor
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.ExploreState
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_empty_query
import muppi.composeapp.generated.resources.tv_genre
import muppi.composeapp.generated.resources.tv_type
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreContentSection(
	modifier: Modifier = Modifier,
	lazyListState: LazyListState,
	state: ExploreState,
	onClickItem: (Int) -> Unit,
	onSelectType: (Boolean) -> Unit,
	onSelectGenres: (List<Int>) -> Unit,
) {

	LazyColumn(
		state = lazyListState,
		modifier = modifier.background(primaryBackground),
		contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
	) {

		item {

			Text(
				text = stringResource(Res.string.tv_type),
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_regular)),
					fontSize = 13.sp,
					color = inactiveDarkColor,
				)
			)

			Spacer(modifier = Modifier.height(8.dp))

			FilterTypeSection {
				onSelectType(it)
			}

			Spacer(modifier = Modifier.height(16.dp))

		}

		stickyHeader {

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.background(primaryBackground)
			){

				Text(
					text = stringResource(Res.string.tv_genre),
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_regular)),
						fontSize = 13.sp,
						color = inactiveDarkColor,
					)
				)

				Spacer(modifier = Modifier.height(8.dp))

				FilterGenreSection(genres = state.genres) {
					onSelectGenres(it)
				}

				Spacer(modifier = Modifier.height(16.dp))
			}

		}

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
						modifier = Modifier.fillMaxSize().padding(top = 24.dp)
					)
				}
			}
		}

		if (state.movies.isEmpty() && state.moviesState !is UiState.Loading && state.moviesState !is UiState.Error) {
			item {
				ErrorComponent(
					title = stringResource(Res.string.tv_empty_query),
					imageModifier = Modifier.size(50.dp),
					modifier = Modifier.fillMaxWidth().height(270.dp)
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

		item {
			if (state.nextPageState is UiState.Loading) {
				PulseAnimation()
			}
		}

		item {
			Spacer(modifier = Modifier.height(70.dp))
		}
	}
}
