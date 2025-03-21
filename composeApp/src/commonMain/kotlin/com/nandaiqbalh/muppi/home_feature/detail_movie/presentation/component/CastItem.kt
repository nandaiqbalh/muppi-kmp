package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
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
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.inactiveDarkColor
import com.nandaiqbalh.muppi.core.presentation.onBackground
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.ApiRoutes
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_failed
import muppi.composeapp.generated.resources.nunito_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun CastItem(
	modifier: Modifier = Modifier,
	cast: Cast,
) {

	Column(
		modifier = modifier
			.height(135.dp)
			.width(80.dp)
	) {
		CoilImage(
			imageModel = {
				ApiRoutes.BASE_URL_IMAGE.plus(cast.profilePath)
			},
			imageOptions = ImageOptions(
				contentScale = ContentScale.Crop,
				alignment = Alignment.Center,
				contentDescription = "Cast",
				colorFilter = null,
			),
			failure = {
				Box(
					modifier = Modifier
						.fillMaxSize()
						.background(onBackground),
					contentAlignment = Alignment.Center
				) {

					Image(
						painter = painterResource(Res.drawable.ic_failed),
						contentDescription = null,
					)
				}
			},
			loading = {
				PulseAnimation(
					modifier = Modifier.size(80.dp)
				)
			},
			component = rememberImageComponent {
				+CircularRevealPlugin(
					duration = 1000
				)
			},
			modifier = Modifier.size(80.dp)
				.clip(RoundedCornerShape(16.dp))
				.background(primaryBackground),
		)

		Spacer(modifier = Modifier.height(8.dp))

		Text(
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(max = 40.dp),
			text = cast.name,
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_regular)),
				fontSize = 13.sp,
				color = primaryFont,
				textAlign = TextAlign.Start
			)
		)

		Text(
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(max = 40.dp),
			text = "as ${cast.character}",
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_regular)),
				fontSize = 10.sp,
				color = inactiveDarkColor,
				textAlign = TextAlign.Start
			)
		)
	}
}