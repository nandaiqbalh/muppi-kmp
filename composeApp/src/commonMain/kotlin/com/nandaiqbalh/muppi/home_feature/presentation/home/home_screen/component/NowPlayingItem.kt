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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.VerticalGradientBackground
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_failed
import muppi.composeapp.generated.resources.ic_star
import muppi.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun NowPlayingItem(
	movie: Movie,
	onItemClick: (Int) -> Unit,
) {

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.clickable {
				onItemClick(movie.id)
			}
	) {

		CoilImage(
			imageModel = {
				ApiRoutes.BASE_URL_IMAGE.plus(movie.posterPath)
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
					modifier = Modifier.height(350.dp).fillMaxWidth()
				)
			},
			modifier = Modifier.height(350.dp).fillMaxWidth()
				.background(primaryBackground),
		)

		VerticalGradientBackground(
			modifier = Modifier.align(Alignment.BottomCenter)
		)

		Column(
			modifier = Modifier
				.fillMaxWidth()
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
					fontSize = 25.sp,
					color = primaryFont,
					textAlign = TextAlign.Start
				)
			)

			Spacer(modifier = Modifier.height(8.dp))

			Row(
				modifier = Modifier
					.fillMaxWidth(0.3f)

			){

				Image(
					painter = painterResource(Res.drawable.ic_star),
					contentDescription = "Star",
				)

				Spacer(modifier = Modifier.width(4.dp))

				Text(
					text = "${(movie.voteAverage * 10).toInt() / 10.0}",
					modifier = Modifier,
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
						fontSize = 13.sp,
						color = primaryColor,
					)
				)

				Spacer(modifier = Modifier.width(4.dp))

				Text(
					text = "(${movie.voteCount}) reviews",
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
	}
}