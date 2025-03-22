package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.cast_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nandaiqbalh.muppi.core.presentation.inactiveDarkColor
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.cast_screen.component.CastDetailContentSection
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.cast_screen.component.CombinedCreditsSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_movies_by_cast
import muppi.composeapp.generated.resources.tv_similar_movies
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CastDetailScreenRoot(
	viewModel: CastDetailViewModel,
	castId: Int,
	onClickBack: () -> Unit,
	onClickMovie: (Int, Boolean) -> Unit,
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	LaunchedEffect(castId){
		viewModel.getDetailCast(castId)
		viewModel.getCombinedList(castId)
	}

	CastDetailScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is CastDetailAction.OnClickBack -> {
					onClickBack()
				}
				is CastDetailAction.OnClickMovie -> {
					onClickMovie(action.id, action.isMovie)
				}

			}

			viewModel.onAction(action)
		}
	)
}

@Composable
fun CastDetailScreen(
	state: CastDetailState,
	onAction: (CastDetailAction) -> Unit,
) {

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryBackground)
	) {

		Scaffold(
			contentColor = primaryBackground,
			topBar = {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.statusBarsPadding()
						.padding(start = 16.dp, end = 16.dp, top = 16.dp),
					horizontalArrangement = Arrangement.Start
				) {
					Image(
						modifier = Modifier.clip(CircleShape)
							.clickable {
								onAction(CastDetailAction.OnClickBack)
							},
						painter = painterResource(Res.drawable.ic_back),
						contentDescription = null,
					)

				}
			}
		) {

			LazyColumn(
				modifier = Modifier.fillMaxSize().background(primaryBackground)
			){
				item {
					CastDetailContentSection(
						castDetailState = state.castDetailState
					)

					Spacer(modifier = Modifier.height(16.dp))

					Text(
						modifier = Modifier.fillMaxWidth()
							.padding(horizontal = 16.dp),
						text = stringResource(Res.string.tv_movies_by_cast),
						style = TextStyle(
							fontFamily = FontFamily(Font(Res.font.nunito_regular)),
							fontSize = 10.sp,
							color = inactiveDarkColor,
						)
					)

					Spacer(modifier = Modifier.height(8.dp))

					CombinedCreditsSection(
						moviesState = state.moviesState,
						onClickItem = { id ->
							onAction(CastDetailAction.OnClickMovie(id, isMovie = true))
						}
					)

					Spacer(modifier = Modifier.height(32.dp))

				}
			}

		}
	}
}