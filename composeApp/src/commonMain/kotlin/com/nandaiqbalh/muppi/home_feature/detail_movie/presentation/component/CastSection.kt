package com.nandaiqbalh.muppi.home_feature.detail_movie.presentation.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.domain.UiState
import com.nandaiqbalh.muppi.core.domain.model.Cast
import com.nandaiqbalh.muppi.core.presentation.components.ErrorComponent
import com.nandaiqbalh.muppi.core.presentation.components.PulseAnimation
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_error_empty
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun CastSection(
	castState: UiState<List<Cast>>,
	onClickItem: (Int) -> Unit,
) {

	when (castState) {
		is UiState.Success -> {
			val casts = castState.data

			if (casts.isNotEmpty()){
				LazyRow(
					modifier = Modifier.fillMaxWidth(),
					contentPadding = PaddingValues(horizontal = 16.dp)
				) {
					itemsIndexed(casts) { index, cast ->

						CastItem(
							modifier = Modifier
								.clickable {
									onClickItem(cast.id)
								},
							cast = cast,
						)

						// Conditional spacer after each item, except the last one
						if (index != casts.lastIndex) {
							Spacer(modifier = Modifier.width(16.dp))
						}
					}
				}
			} else {
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

		is UiState.Error -> {
			Box(
				modifier = Modifier
					.background(primaryBackground),
				contentAlignment = Alignment.Center
			){
				ErrorComponent(
					modifier = Modifier.fillMaxWidth().height(120.dp)
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
					modifier = Modifier.height(120.dp).fillMaxWidth()
				)
			}
		}
	}

}