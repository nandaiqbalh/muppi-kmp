package com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen

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
import com.nandaiqbalh.muppi.core.presentation.components.SearchCard
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.saved_feature.presentation.saved_screen.component.SavedMovieContentSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_search
import muppi.composeapp.generated.resources.nav_item_saved
import muppi.composeapp.generated.resources.nunito_medium
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SavedMovieScreenRoot(
	viewModel: SavedMovieViewModel,
	onClickItem: (Int, Boolean) -> Unit
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	SavedMovieScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is SavedMovieAction.OnClickItem -> {
					onClickItem(action.id, state.isMovie)
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
fun SavedMovieScreen(
	state: SavedMovieState,
	onAction: (SavedMovieAction) -> Unit,
) {

	LaunchedEffect(state.keyword, state.isMovie) {

		onAction(
			SavedMovieAction.GetSavedMovies(
				isMovie = state.isMovie,
				query = state.keyword
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
								text = stringResource(Res.string.nav_item_saved),
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
										onAction(SavedMovieAction.OnClickSearchIcon(!state.isSearchFieldVisible))
										onAction(SavedMovieAction.OnQueryChange(""))
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
							placeholder = "Search",
							onCancelSearch = {
								onAction(SavedMovieAction.OnClickSearchIcon(!state.isSearchFieldVisible))
							},
							onValueChanged = { text ->
								onAction(SavedMovieAction.OnQueryChange(text))
							}
						)
					}
				}
			}
		) {

			val lazyListState = rememberLazyListState()

			SavedMovieContentSection(
				modifier = Modifier
					.background(primaryBackground)
					.padding(it),
				lazyListState = lazyListState,
				state = state,
				onClickItem = { id ->
					onAction(SavedMovieAction.OnClickItem(id))
				},
				onSelectType = { isMovie ->
					onAction(SavedMovieAction.OnSelectType(isMovie))
				}
			)
		}
	}
}