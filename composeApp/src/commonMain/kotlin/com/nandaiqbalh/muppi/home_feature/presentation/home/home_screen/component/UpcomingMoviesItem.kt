package com.nandaiqbalh.muppi.home_feature.presentation.home.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.domain.model.getGenreNamesByIds
import com.nandaiqbalh.muppi.core.presentation.components.GenreChip
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.VerticalGradientBackground
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.nandaiqbalh.muppi.core.utils.toFormattedDate
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_failed
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun UpcomingMoviesItem(
	movie: Movie,
	onItemClick: (Int) -> Unit,
) {

	Box(
		modifier = Modifier
			.width(270.dp).height(185.dp)
			.clickable {
				onItemClick(movie.id)
			}
	) {

		CoilImage(
			imageModel = {
				ApiRoutes.BASE_URL_IMAGE.plus(movie.backdropPath)
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
				){

					Image(
						painter = painterResource(Res.drawable.ic_failed),
						contentDescription = null,
					)
				}
			},
			component = rememberImageComponent {
				+CircularRevealPlugin(
					duration = 1000
				)
			},
			loading = {
				PulseAnimation(
					modifier = Modifier.height(185.dp).width(270.dp)
				)
			},
			modifier = Modifier.width(270.dp).height(185.dp)
				.clip(RoundedCornerShape(16.dp))
				.background(primaryBackground),
		)

		VerticalGradientBackground(
			modifier = Modifier
				.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
				.align(Alignment.BottomCenter)
		)

		Column(
			modifier = Modifier
				.width(270.dp)
				.padding(16.dp)
				.align(Alignment.BottomCenter),
		){

			Text(
				text = movie.title,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier
					.fillMaxWidth(0.8f),
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
					fontSize = 16.sp,
					color = primaryFont,
					textAlign = TextAlign.Start
				)
			)

			Spacer(modifier = Modifier.height(8.dp))

			Row(
				modifier = Modifier,
				verticalAlignment = Alignment.CenterVertically
			){

				val genreName = getGenreNamesByIds(movie.genreIds.take(1))

				genreName.forEach {name ->
					GenreChip(
						genre = name,
						modifier = Modifier
					)

					Spacer(modifier = Modifier.width(4.dp))
				}

				Text(
					text = movie.releaseDate.toFormattedDate(),
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
					modifier = Modifier,
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_regular)),
						fontSize = 10.sp,
						color = primaryFont,
					)
				)
			}
		}
	}
}
