package com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.logging
import com.nandaiqbalh.muppi.home_feature.presentation.home.list_screen.component.ListMovieContentSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.ic_search
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.tv_series_on_air
import muppi.composeapp.generated.resources.tv_top_rated
import muppi.composeapp.generated.resources.tv_upcoming_movies
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ListMovieScreenRoot(
	viewModel: ListMovieViewModel,
	source: Int,
	onClickBack: () -> Unit,
	onClickSearch: () -> Unit,
	onClickMovieItem: (Int, Boolean) -> Unit
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	LaunchedEffect(source){
		viewModel.updateState { it.copy(source = source, moviesState = UiState.Loading) }
	}

	ListMovieScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is ListMovieAction.OnClickBack -> {
					onClickBack()
				}
				is ListMovieAction.OnClickSearch -> {
					onClickSearch()
				}
				is ListMovieAction.OnClickItem -> {
					onClickMovieItem(action.id, state.source != 3)
				}
				else -> {
					Unit
				}
			}

			viewModel.onAction(action)
		}
	)
}

@Composable
fun ListMovieScreen(
	state: ListMovieState,
	onAction: (ListMovieAction) -> Unit,
) {

	LaunchedEffect(state.source){
		onAction(
			ListMovieAction.GetMovieList(
				page = state.page,
				source = state.source,
				isFirstLoad = true
			)
		)
	}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryBackground)
	) {

		Scaffold(
			containerColor = primaryBackground,
			contentColor = primaryBackground,
			topBar = {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.statusBarsPadding()
						.padding(start = 16.dp, end = 16.dp, top = 16.dp),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								onAction(ListMovieAction.OnClickBack)
							},
						painter = painterResource(Res.drawable.ic_back),
						contentDescription = null,
					)

					val titleScreen: String = when(state.source){
						1 -> stringResource(Res.string.tv_top_rated)
						2 -> stringResource(Res.string.tv_upcoming_movies)
						3 -> stringResource(Res.string.tv_series_on_air)
						else -> {""}
					}

					Text(
						modifier = Modifier
							.weight(1f),
						text = titleScreen,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis,
						style = TextStyle(
							fontFamily = FontFamily(Font(Res.font.nunito_medium)),
							fontSize = 20.sp,
							color = primaryFont,
							textAlign = TextAlign.Center
						)
					)

					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								onAction(ListMovieAction.OnClickSearch)
							},
						painter = painterResource(Res.drawable.ic_search),
						contentDescription = null,
					)

				}
			}
		) {

			val lazyListState = rememberLazyListState()

			val isAtBottom = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
			val isReadyToScroll =
				state.moviesState is UiState.Success &&
						state.movies.size > 10 &&
						state.moviesState !is UiState.Loading &&
						state.moviesState !is UiState.Initial &&
						state.nextPageState !is UiState.Loading &&
						state.nextPageState !is UiState.Initial

			val isNeedScroll = isAtBottom && isReadyToScroll

			LaunchedEffect(isNeedScroll){
				onAction(
					ListMovieAction.GetMovieList(
						page = state.page,
						source = state.source,
						isFirstLoad = false
					)
				)
			}

			ListMovieContentSection(
				modifier = Modifier
					.padding(it),
				lazyListState = lazyListState,
				state = state,
				onClickItem = { id ->
					onAction(ListMovieAction.OnClickItem(id))
				}
			)
		}
	}
}