package com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nandaiqbalh.muppi.core.data.mapper.toMovie
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.CustomAnimatedDialog
import com.nandaiqbalh.muppi.core.presentation.components.GeneralDialogState
import com.nandaiqbalh.muppi.core.presentation.components.PreventUserInteractionComponent
import com.nandaiqbalh.muppi.core.presentation.components.rememberGeneralDialogState
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.utils.Constant
import com.nandaiqbalh.muppi.home_feature.presentation.detail.detail_screen.component.DetailContentSection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.cancel
import muppi.composeapp.generated.resources.delete
import muppi.composeapp.generated.resources.delete_movie_description
import muppi.composeapp.generated.resources.delete_movie_title
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.ic_delete_saved
import muppi.composeapp.generated.resources.ic_save_inactive
import muppi.composeapp.generated.resources.movie_deleted_description
import muppi.composeapp.generated.resources.movie_deleted_title
import muppi.composeapp.generated.resources.movie_saved_description
import muppi.composeapp.generated.resources.movie_saved_title
import muppi.composeapp.generated.resources.ok
import muppi.composeapp.generated.resources.save
import muppi.composeapp.generated.resources.save_movie_description
import muppi.composeapp.generated.resources.save_movie_title
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailScreenRoot(
	viewModel: DetailViewModel,
	movieId: Int,
	isMovie: Boolean,
	onClickBack: () -> Unit,
	onClickCast: (Int) -> Unit,
	onClickSimilarMovie: (Int, Boolean) -> Unit,
) {

	val state by viewModel.state.collectAsStateWithLifecycle()
	val dialogState = rememberGeneralDialogState()
	val scope = rememberCoroutineScope()

	LaunchedEffect(movieId, isMovie){
		viewModel.getDetailMovie(movieId, isMovie)
		viewModel.getMovieCasts(movieId, isMovie)
		viewModel.getSimilarMovies(movieId, isMovie)
		viewModel.getVideos(movieId, isMovie)
		viewModel.checkIsFavorite(movieId)
	}

	DetailScreen(
		state = state,
		dialogState = dialogState,
		coroutineScope = scope,
		onAction = { action ->
			when (action) {
				is DetailAction.OnClickBack -> {
					onClickBack()
				}

				is DetailAction.OnClickCast -> {
					onClickCast(action.id)
				}

				is DetailAction.OnClickSimilar -> {
					onClickSimilarMovie(action.id, isMovie)
				}
				is DetailAction.OnSaveMovie -> {
					viewModel.saveMovie(action.movie.toMovie().copy(isMovie = isMovie))
				}
				is DetailAction.OnDeleteMovie -> {
					scope.launch {
						dialogState.apply {
							showDialog = true
							type = Constant.DialogType.WARNING
							title = getString(Res.string.delete_movie_title)
							description = getString(Res.string.delete_movie_description)
							buttonDismiss = getString(Res.string.cancel)
							onClickDismiss = {
								showDialog = false
							}
							buttonConfirm = getString(Res.string.delete)
							onClickConfirm = {
								showDialog = false
								viewModel.deleteMovie(action.id)
							}
						}
					}
				}
			}
			viewModel.onAction(action)
		}
	)
}

@Composable
fun DetailScreen(
	state: DetailState,
	dialogState: GeneralDialogState,
	coroutineScope: CoroutineScope,
	onAction: (DetailAction) -> Unit,
) {

	val screenDialogState = rememberGeneralDialogState()

	LaunchedEffect(state.saveState) {
		if (state.saveState is UiState.Success) {
			coroutineScope.launch {
				screenDialogState.apply {
					showDialog = true
					type = Constant.DialogType.SUCCESS
					title = getString(Res.string.movie_saved_title)
					description = getString(Res.string.movie_saved_description)
					buttonConfirm = getString(Res.string.ok)
					onClickConfirm = {
						showDialog = false
						state.saveState = UiState.Initial
					}
				}
			}
		}
	}

	LaunchedEffect(state.deleteState) {
		if (state.deleteState is UiState.Success) {
			coroutineScope.launch {
				screenDialogState.apply {
					showDialog = true
					type = Constant.DialogType.SUCCESS
					title = getString(Res.string.movie_deleted_title)
					description = getString(Res.string.movie_deleted_description)
					buttonConfirm = getString(Res.string.ok)
					onClickConfirm = {
						showDialog = false
						state.deleteState = UiState.Initial
					}
				}
			}
		}
	}


	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryBackground)
	) {

		Scaffold(
			modifier = Modifier.background(primaryBackground),
			topBar = {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.statusBarsPadding()
						.padding(start = 16.dp, end = 16.dp, top = 16.dp),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								onAction(DetailAction.OnClickBack)
							},
						painter = painterResource(Res.drawable.ic_back),
						contentDescription = null,
					)


					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								if (state.detailMovie is UiState.Success) {
									if (state.isFavorite){
										onAction(DetailAction.OnDeleteMovie(state.detailMovie.data.id))
									} else {
										onAction(DetailAction.OnSaveMovie(state.detailMovie.data))
									}
								}
							},
						painter = if (state.isFavorite) painterResource(Res.drawable.ic_delete_saved) else painterResource(Res.drawable.ic_save_inactive),
						contentDescription = null,
					)
				}
			}
		) {

			DetailContentSection(
				state = state,
				onClickCastItem = { id ->
					onAction(DetailAction.OnClickCast(id))
				},
				onClickSimilarMovieItem = { id ->
					onAction(DetailAction.OnClickSimilar(id))
				}
			)

		}

		PreventUserInteractionComponent(
			isPreventUserInteraction = state.saveState == UiState.Loading
					|| state.deleteState == UiState.Loading,
			isNeedIndicator = true
		)

		CustomAnimatedDialog(screenDialogState)
		CustomAnimatedDialog(dialogState)
	}
}