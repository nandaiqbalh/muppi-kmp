package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nandaiqbalh.muppi.core.presentation.UiState
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.component.DetailMovieContentSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.ic_save_inactive
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailMovieScreenRoot(
	viewModel: DetailMovieViewModel,
	onClickBack: () -> Unit,
	onClickCast: (Int) -> Unit,
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	DetailMovieScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is DetailMovieAction.OnClickBack -> {
					onClickBack()
				}

				is DetailMovieAction.OnClickCast -> {
					onClickCast(action.id)
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
fun DetailMovieScreen(
	state: DetailMovieState,
	onAction: (DetailMovieAction) -> Unit,
) {

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
								onAction(DetailMovieAction.OnClickBack)
							},
						painter = painterResource(Res.drawable.ic_back),
						contentDescription = null,
					)


					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								if (state.detailMovie is UiState.Success) {
									onAction(DetailMovieAction.OnClickSave(state.detailMovie.data))
								}
							},
						painter = painterResource(Res.drawable.ic_save_inactive),
						contentDescription = null,
					)
				}
			}
		) {

			DetailMovieContentSection(
				state = state,
				onClickCastItem = { id ->
					onAction(DetailMovieAction.OnClickCast(id))
				},
				onClickSimilarMovieItem = { id ->
					onAction(DetailMovieAction.OnClickSimilarMovie(id))
				}
			)

		}
	}
}