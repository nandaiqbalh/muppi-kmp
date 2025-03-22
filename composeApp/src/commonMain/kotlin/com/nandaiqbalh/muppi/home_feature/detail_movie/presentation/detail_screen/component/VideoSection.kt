package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.detail_screen.component

import VideoPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Video
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.components.items.MovieWithTitleItem
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_error_empty
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun VideoSection(
	videosState: UiState<List<Video>> = UiState.Initial
) {

	when (videosState) {
		is UiState.Success -> {
			val videos = videosState.data

			val video = videos.find { it.type == "Trailer" }
				?: videos.firstOrNull() // If no trailer is found, get the first item or null
				?: Video(name = "", key = "", site = "", size = 0, type = "Trailer", official = false, id = "") // If list is empty, create an empty trailer

			val key = video.key

			if (key.isNotEmpty()){
				Box(
					modifier = Modifier.fillMaxWidth()
						.padding(horizontal = 16.dp).height(200.dp)
						.clip(RoundedCornerShape(16.dp)),
				) {
					VideoPlayer(
						modifier = Modifier,
						url = "https://www.youtube.com/watch?v=$key",
						autoPlay = false,
						showControls = true
					)
				}
			} else {
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.background(primaryBackground)
				){
					Text(
						text = stringResource(Res.string.tv_error_empty),
						modifier = Modifier.padding(16.dp),
						style = TextStyle(
							fontFamily = FontFamily(Font(Res.font.nunito_regular)),
							fontSize = 10.sp,
							color = primaryFont,
							textAlign = TextAlign.Start
						)
					)
				}
			}
		}

		is UiState.Error -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){
				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(250.dp)
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
					modifier = Modifier.height(250.dp).fillMaxWidth()
				)
			}
		}
	}


}