package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.GenreChip
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.SingleHeaderText
import com.nandaiqbalh.muppi.core.presentation.components.VerticalGradientBackground
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.core.utils.toFormattedDate
import com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.DetailMovieState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_failed
import muppi.composeapp.generated.resources.ic_star
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.nunito_semibold
import muppi.composeapp.generated.resources.tv_cast
import muppi.composeapp.generated.resources.tv_error_general
import muppi.composeapp.generated.resources.tv_similar_movies
import muppi.composeapp.generated.resources.tv_storyline
import muppi.composeapp.generated.resources.tv_trailer
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailMovieContentSection(
	state: DetailMovieState,
	onClickCastItem: (Int) -> Unit,
	onClickSimilarMovieItem: (Int) -> Unit
) {

	when (state.detailMovie) {
		is UiState.Success -> {

			val detailMovie = state.detailMovie.data

			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
					.background(primaryBackground)
					.navigationBarsPadding()
			) {

				item {

					Box(
						modifier = Modifier
							.fillMaxWidth()
							.height(450.dp)
					) {

						CoilImage(
							imageModel = {
								ApiRoutes.BASE_URL_IMAGE.plus(detailMovie.posterPath)
							},
							imageOptions = ImageOptions(
								contentScale = ContentScale.Crop,
								alignment = Alignment.Center,
								contentDescription = "Movie item",
								colorFilter = null,
							),
							failure = {
								Box(
									modifier = Modifier
										.fillMaxSize(),
									contentAlignment = Alignment.Center
								) {

									Column(
										modifier = Modifier
											.fillMaxSize(),
										horizontalAlignment = Alignment.CenterHorizontally,
										verticalArrangement = Arrangement.Center
									) {

										Image(
											painter = painterResource(Res.drawable.ic_failed),
											contentDescription = null,
										)

										Spacer(modifier = Modifier.height(4.dp))

										Text(
											modifier = Modifier
												.fillMaxWidth(),
											text = stringResource(Res.string.tv_error_general),
											style = TextStyle(
												fontFamily = FontFamily(Font(Res.font.nunito_medium)),
												fontSize = 10.sp,
												color = primaryFont,
												textAlign = TextAlign.Center
											)
										)
									}


								}
							},
							component = rememberImageComponent {
								+CircularRevealPlugin(
									duration = 1000
								)
							},
							loading = {
								PulseAnimation(
									modifier = Modifier.height(450.dp).fillMaxWidth()
								)
							},
							modifier = Modifier.height(450.dp).fillMaxWidth()
								.background(primaryBackground),
						)

						VerticalGradientBackground(
							height = 110,
							modifier = Modifier.align(Alignment.BottomCenter)
						)

						Column(
							modifier = Modifier
								.fillMaxWidth()
								.padding(16.dp)
								.align(Alignment.BottomCenter),
						) {

							Text(
								text = detailMovie.title,
								maxLines = 1,
								overflow = TextOverflow.Ellipsis,
								modifier = Modifier,
								style = TextStyle(
									fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
									fontSize = 25.sp,
									color = primaryFont,
									textAlign = TextAlign.Start
								)
							)

							Spacer(modifier = Modifier.height(8.dp))

							Row(
								modifier = Modifier
									.fillMaxWidth(),
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.spacedBy(16.dp)
							) {
								Text(
									text = detailMovie.releaseDate.toFormattedDate(),
									maxLines = 1,
									overflow = TextOverflow.Ellipsis,
									modifier = Modifier,
									style = TextStyle(
										fontFamily = FontFamily(Font(Res.font.nunito_regular)),
										fontSize = 13.sp,
										color = primaryFont,
										textAlign = TextAlign.Start
									)
								)

								Text(
									text = "|",
									maxLines = 1,
									overflow = TextOverflow.Ellipsis,
									modifier = Modifier,
									style = TextStyle(
										fontFamily = FontFamily(Font(Res.font.nunito_regular)),
										fontSize = 13.sp,
										color = primaryFont,
										textAlign = TextAlign.Start
									)
								)

								Row(
									modifier = Modifier
								) {

									Image(
										painter = painterResource(Res.drawable.ic_star),
										contentDescription = "Star",
									)

									Spacer(modifier = Modifier.width(4.dp))

									Text(
										text = "${(detailMovie.voteAverage * 10).toInt() / 10.0}",
										modifier = Modifier,
										style = TextStyle(
											fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
											fontSize = 13.sp,
											color = primaryColor,
										)
									)

									Spacer(modifier = Modifier.width(4.dp))

									Text(
										text = "(${detailMovie.voteCount}) reviews",
										modifier = Modifier
											.offset(y = 2.dp),
										style = TextStyle(
											fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
											fontSize = 10.sp,
											color = inactiveColor,
										)
									)

								}

							}

							Spacer(modifier = Modifier.height(4.dp))

							FlowRow(
								modifier = Modifier
									.fillMaxWidth()
							) {

								// Ambil maksimal 3 genre jika lebih dari 3, jika kurang atau sama dengan 3 ambil semua
								val genresToDisplay = detailMovie.genres.take(3)

								genresToDisplay.forEach { genre ->
									GenreChip(
										genre = genre.name, // Pastikan 'genre' mengacu pada objek yang memiliki properti 'name'
										modifier = Modifier
									)

									Spacer(modifier = Modifier.width(4.dp))
								}

							}
						}
					}

					Spacer(modifier = Modifier.height(16.dp))

					SingleHeaderText(
						stringResource(Res.string.tv_storyline)
					)

					Spacer(modifier = Modifier.height(8.dp))

					Text(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 16.dp),
						text = detailMovie.overview,
						style = TextStyle(
							fontFamily = FontFamily(Font(Res.font.nunito_regular)),
							fontSize = 13.sp,
							color = primaryFont,
							textAlign = TextAlign.Justify
						)
					)

					Spacer(modifier = Modifier.height(16.dp))

					SingleHeaderText(
						stringResource(Res.string.tv_trailer)
					)

					Spacer(modifier = Modifier.height(8.dp))

					// media player for trailer

					Spacer(modifier = Modifier.height(16.dp))

					SingleHeaderText(
						stringResource(Res.string.tv_cast)
					)

					Spacer(modifier = Modifier.height(8.dp))

					CastSection(
						castState = state.casts,
						onClickItem = { id ->
							onClickCastItem(id)
						}
					)

					Spacer(modifier = Modifier.height(16.dp))

					SingleHeaderText(
						stringResource(Res.string.tv_similar_movies)
					)

					Spacer(modifier = Modifier.height(8.dp))

					SimilarMoviesSection(
						similarMoviesState = state.similarMovies,
						onClickItem = { id ->
							onClickSimilarMovieItem(id)
						}
					)

					Spacer(modifier = Modifier.height(32.dp))

				}
			}
		}

		is UiState.Error -> {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(primaryBackground),
				contentAlignment = Alignment.TopCenter
			){
				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(450.dp)
				)
			}
		}

		else -> {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(primaryBackground),
				contentAlignment = Alignment.TopCenter
			){

				PulseAnimation(
					modifier = Modifier.height(450.dp).fillMaxWidth()
				)
			}
		}
	}

}