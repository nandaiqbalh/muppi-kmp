package com.nandaiqbalh.muppi.home_feature.presentation.detail.cast_screen.component

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.CastDetail
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.SingleHeaderText
import com.nandaiqbalh.muppi.core.presentation.components.TitleValueText
import com.nandaiqbalh.muppi.core.presentation.onBackground
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
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_birthday
import muppi.composeapp.generated.resources.tv_place_of_birth
import muppi.composeapp.generated.resources.tv_similar_movies
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CastDetailContentSection(
	castDetailState: UiState<CastDetail>,
) {

	when (castDetailState) {
		is UiState.Success -> {

			val cast = castDetailState.data

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.statusBarsPadding()
					.padding(top = 40.dp)
					.padding(horizontal = 16.dp),
				horizontalAlignment = Alignment.CenterHorizontally
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
							modifier = Modifier.width(154.dp).height(217.dp)
						)
					},
					component = rememberImageComponent {
						+CircularRevealPlugin(
							duration = 1000
						)
					},
					modifier = Modifier.width(154.dp).height(217.dp)
						.clip(RoundedCornerShape(16.dp))
						.background(primaryBackground)
						.align(Alignment.CenterHorizontally),
				)

				Spacer(modifier = Modifier.height(16.dp))

				Text(
					modifier = Modifier
						.fillMaxWidth()
						.heightIn(max = 40.dp),
					text = cast.name,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_medium)),
						fontSize = 20.sp,
						color = primaryFont,
						textAlign = TextAlign.Center
					)
				)

				Spacer(modifier = Modifier.height(4.dp))

				FlowRow(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally), // Adjust spacing between items
					verticalArrangement = Arrangement.Center // Ensure vertical alignment
				) {
					// Iterate through the aliases list
					cast.alsoKnownAs.forEachIndexed { index, alias ->
						Text(
							modifier = Modifier
								.heightIn(max = 40.dp), // Restrict the height to 40dp for each text
							text = alias,
							maxLines = 1,
							overflow = TextOverflow.Ellipsis,
							style = TextStyle(
								fontFamily = FontFamily(Font(Res.font.nunito_regular)),
								fontSize = 13.sp,
								color = primaryFont,
								textAlign = TextAlign.Center
							)
						)

						// Add the separator "|" only after each alias except the last one
						if (index < cast.alsoKnownAs.size - 1) {
							Text(
								modifier = Modifier
									.heightIn(max = 40.dp),
								text = "|",
								maxLines = 1,
								overflow = TextOverflow.Ellipsis,
								style = TextStyle(
									fontFamily = FontFamily(Font(Res.font.nunito_regular)),
									fontSize = 13.sp,
									color = primaryFont,
									textAlign = TextAlign.Center
								)
							)
						}
					}
				}

				Spacer(modifier = Modifier.height(16.dp))

				TitleValueText(
					title = stringResource(Res.string.tv_place_of_birth),
					value = cast.placeOfBirth
				)

				Spacer(modifier = Modifier.height(8.dp))

				TitleValueText(
					title = stringResource(Res.string.tv_birthday),
					value = cast.birthday.toFormattedDate()
				)

				Spacer(modifier = Modifier.height(8.dp))

				TitleValueText(
					title = stringResource(Res.string.tv_birthday),
					value = cast.biography
				)

			}
		}

		is UiState.Error -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){
				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(300.dp)
				)
			}
		}

		else -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){

				PulseAnimation(
					modifier = Modifier.height(300.dp).fillMaxWidth()
				)
			}
		}
	}
}