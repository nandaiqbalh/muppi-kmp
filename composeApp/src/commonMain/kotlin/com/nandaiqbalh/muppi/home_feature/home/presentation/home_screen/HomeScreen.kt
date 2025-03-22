package com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nandaiqbalh.muppi.core.domain.model.SeeAllSource
import com.nandaiqbalh.muppi.core.presentation.components.HeaderText
import com.nandaiqbalh.muppi.core.presentation.components.IconChip
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen.component.NowPlayingSection
import com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen.component.SeriesOnAirSection
import com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen.component.TopRatedSection
import com.nandaiqbalh.muppi.home_feature.home.presentation.home_screen.component.UpComingMoviesSection
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.app_name
import muppi.composeapp.generated.resources.iv_logo_home
import muppi.composeapp.generated.resources.tv_see_all
import muppi.composeapp.generated.resources.tv_series_on_air
import muppi.composeapp.generated.resources.tv_top_rated
import muppi.composeapp.generated.resources.tv_upcoming_movies
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreenRoot(
	viewModel: HomeScreenViewModel,
	onClickItem: (Int, Boolean) -> Unit,
	onClickSeeAll: (SeeAllSource) -> Unit,
) {

	val state by viewModel.state.collectAsStateWithLifecycle()

	LaunchedEffect(Unit){
		viewModel.getNowPlayingMovies()
		viewModel.getUpcomingMovies()
		viewModel.getTopRatedMovies()
		viewModel.getOnAirTV()
	}

	HomeScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is HomeScreenAction.OnClickItem -> {
					onClickItem(action.id, action.isMovie)
				}

				is HomeScreenAction.OnClickSeeAll -> {
					onClickSeeAll(action.source)
				}

			}

			viewModel.onAction(action)
		}
	)
}

@Composable
fun HomeScreen(
	state: HomeScreenState,
	onAction: (HomeScreenAction) -> Unit,
) {

	Box(
		modifier = Modifier
			.fillMaxSize()
	){

		LazyColumn(
			modifier = Modifier
				.fillMaxSize()
				.background(primaryBackground)
				.navigationBarsPadding()
		) {

			item {
				// now playing
				NowPlayingSection(
					modifier = Modifier.fillMaxWidth(),
					moviesState = state.nowPlaying,
					onItemClick = { id ->
						onAction(HomeScreenAction.OnClickItem(id, isMovie = true))
					}
				)

				Spacer(modifier = Modifier.height(16.dp))

				HeaderText(
					title = stringResource(Res.string.tv_top_rated),
					actionText = stringResource(Res.string.tv_see_all),
					onActionClick = {
						onAction(HomeScreenAction.OnClickSeeAll(SeeAllSource.TopRated))
					}
				)

				Spacer(modifier = Modifier.height(8.dp))

				TopRatedSection(
					moviesState = state.topRated,
					onItemClick = { id ->
						onAction(HomeScreenAction.OnClickItem(id, isMovie = true))
					}
				)

				Spacer(modifier = Modifier.height(16.dp))

				HeaderText(
					title = stringResource(Res.string.tv_upcoming_movies),
					actionText = stringResource(Res.string.tv_see_all),
					onActionClick = {
						onAction(HomeScreenAction.OnClickSeeAll(SeeAllSource.UpcomingMovies))
					}
				)

				Spacer(modifier = Modifier.height(8.dp))

				UpComingMoviesSection(
					moviesState = state.upcomingMovies,
					onItemClick = { id ->
						onAction(HomeScreenAction.OnClickItem(id, isMovie = true))
					}
				)

				Spacer(modifier = Modifier.height(16.dp))

				HeaderText(
					title = stringResource(Res.string.tv_series_on_air),
					actionText = stringResource(Res.string.tv_see_all),
					onActionClick = {
						onAction(HomeScreenAction.OnClickSeeAll(SeeAllSource.SeriesOnAir))
					}
				)

				Spacer(modifier = Modifier.height(8.dp))

				SeriesOnAirSection(
					moviesState = state.onAirTv,
					onItemClick = { id ->
						onAction(HomeScreenAction.OnClickItem(id, isMovie = false))
					}
				)

				Spacer(modifier = Modifier.height(100.dp))

			}
		}

		IconChip(
			logo = Res.drawable.iv_logo_home,
			text = stringResource(Res.string.app_name),
			modifier = Modifier
				.align(Alignment.TopStart)
				.statusBarsPadding()
				.padding(start = 16.dp, top = 8.dp)
		)
	}
}