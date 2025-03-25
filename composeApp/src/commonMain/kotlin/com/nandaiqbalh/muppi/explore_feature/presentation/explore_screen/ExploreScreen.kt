package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.nandaiqbalh.muppi.core.presentation.components.PreventUserInteractionComponent
import com.nandaiqbalh.muppi.core.presentation.components.SearchCard
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.component.ExploreContentSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_search
import muppi.composeapp.generated.resources.nav_item_explore
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.tv_search
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExploreScreenRoot(
	viewModel: ExploreViewModel,
	onClickMovieItem: (Int, Boolean) -> Unit,
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	ExploreScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is ExploreAction.OnClickItem -> {
					onClickMovieItem(action.id, state.isMovie)
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
fun ExploreScreen(
	state: ExploreState,
	onAction: (ExploreAction) -> Unit,
) {
	LaunchedEffect(state.keyword, state.isMovie, state.genres) {
		onAction(
			ExploreAction.ExploreMovieOrTv(
				page = state.page,
				isMovie = state.isMovie,
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
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.height(90.dp)
						.statusBarsPadding()
						.padding(start = 16.dp, end = 16.dp, top = 16.dp),
				) {
					AnimatedVisibility(
						modifier = Modifier
							.fillMaxWidth(),
						visible = (!state.isSearchFieldVisible),
						enter = fadeIn(
							animationSpec = tween(durationMillis = 500)
						),
						exit = fadeOut(
							animationSpec = tween(durationMillis = 500)
						)
					) {

						Box(
							modifier = Modifier.fillMaxWidth(),
						){

							Text(
								modifier = Modifier
									.align(Alignment.Center),
								text = stringResource(Res.string.nav_item_explore),
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
								modifier = Modifier
									.clip(CircleShape)
									.align(Alignment.CenterEnd)
									.clickable {
										onAction(ExploreAction.OnClickSearchIcon(!state.isSearchFieldVisible))

									},
								painter = painterResource(Res.drawable.ic_search),
								contentDescription = null,
							)
						}

					}

					AnimatedVisibility(
						visible = state.isSearchFieldVisible,
						enter = slideInHorizontally(
							initialOffsetX = { it },
							animationSpec = tween(durationMillis = 1000)
						),
						exit = slideOutHorizontally(
							targetOffsetX = { it },
							animationSpec = tween(durationMillis = 1000)
						)
					) {
						SearchCard(modifier = Modifier.fillMaxWidth(),
							placeholder = stringResource(Res.string.tv_search),
							onCancelSearch = {
								onAction(ExploreAction.OnClickSearchIcon(!state.isSearchFieldVisible))
							},
							onValueChanged = { text ->
								onAction(ExploreAction.OnQueryChange(text))
							}
						)
					}
				}
			}
		) {

			val lazyListState = rememberLazyListState()

			val isAtBottom =
				lazyListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
						lazyListState.layoutInfo.visibleItemsInfo.last().index == lazyListState.layoutInfo.totalItemsCount - 1
			val isReadyToScroll =
				state.moviesState is UiState.Success &&
						state.movies.size > 10 &&
						state.nextPageState !is UiState.Loading &&
						state.nextPageState !is UiState.Initial

			val isNeedScroll = isAtBottom && isReadyToScroll

			LaunchedEffect(isNeedScroll){
				if(isNeedScroll){
					onAction(
						ExploreAction.ExploreMovieOrTv(
							page = state.page,
							isMovie = state.isMovie,
							isFirstLoad = false
						)
					)
				}
			}

			LaunchedEffect(isAtBottom) {
				if (isAtBottom && state.movies.size > 10) {
					onAction(ExploreAction.OnReachBottom(state.page + 1))
				}
			}

			ExploreContentSection(
				modifier = Modifier
					.background(primaryBackground)
					.padding(it),
				lazyListState = lazyListState,
				state = state,
				onClickItem = { id ->
					onAction(ExploreAction.OnClickItem(id))
				},
				onSelectGenres = { genres ->
					onAction(ExploreAction.OnSelectGenres(genres))
					onAction(ExploreAction.OnClickSearchIcon(false))
				},
				onSelectType = { isMovie ->
					onAction(ExploreAction.OnSelectType(isMovie))
				}
			)
		}
	}
}