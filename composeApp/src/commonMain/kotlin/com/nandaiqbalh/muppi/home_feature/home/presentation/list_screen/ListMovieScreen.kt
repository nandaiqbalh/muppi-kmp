package com.nandaiqbalh.muppi.home_feature.home.presentation.list_screen

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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.ic_search
import muppi.composeapp.generated.resources.nunito_medium
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListMovieScreenRoot(
	viewModel: ListMovieViewModel,
	source: Int,
	onClickBack: () -> Unit,
	onClickSearch: () -> Unit
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	LaunchedEffect(source){
		when(source){
			1 -> {
				// fetch top rated
				viewModel.updateState { it.copy(titleScreen = "Top Rated") }
			}
			2 -> {
				// fetch upcoming movies
				viewModel.updateState { it.copy(titleScreen = "Upcoming Movies") }
			}
			3 -> {
				// fetch series on air
				viewModel.updateState { it.copy(titleScreen = "Series On Air") }
			}
			else -> {
				// do nothing
			}
		}
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

					Text(
						modifier = Modifier
							.weight(1f),
						text = state.titleScreen,
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

			LazyColumn(
				modifier = Modifier.fillMaxSize().background(primaryBackground)
			){

			}
		}
	}
}