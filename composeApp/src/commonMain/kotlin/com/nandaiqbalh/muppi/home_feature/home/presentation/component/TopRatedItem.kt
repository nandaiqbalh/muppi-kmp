package com.nandaiqbalh.muppi.home_feature.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.shimmerBackground
import com.nandaiqbalh.muppi.core.presentation.onBackground
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
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.tv_error_general
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopRatedItem(
	movie: Movie,
	onItemClick: (Int) -> Unit
) {

	Column(
		modifier = Modifier
			.width(140.dp)
			.height(280.dp)
			.clickable {
				onItemClick(movie.id)
			}
	){
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
						.fillMaxSize()
						.background(onBackground),
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
					modifier = Modifier.height(220.dp).width(140.dp)
				)
			},
			modifier = Modifier.height(220.dp).width(140.dp)
				.clip(RoundedCornerShape(16.dp))
				.background(primaryBackground),
		)

		Spacer(modifier = Modifier.height(8.dp))

		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = movie.title,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_medium)),
				fontSize = 16.sp,
				color = primaryFont,
				textAlign = TextAlign.Start
			)
		)

		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.Start
		){

			Text(
				text = "${(movie.voteAverage * 10).toInt() / 10.0}",
				maxLines = 2,
				overflow = TextOverflow.Ellipsis,
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_medium)),
					fontSize = 13.sp,
					color = primaryColor,
					textAlign = TextAlign.Start
				)
			)

			Text(
				modifier = Modifier
					.offset(y = 2.dp),
				text = " /10",
				maxLines = 2,
				overflow = TextOverflow.Ellipsis,
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_medium)),
					fontSize = 10.sp,
					color = primaryFont,
				)
			)
		}

	}

}