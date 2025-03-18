package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.utils.onBackground
import com.nandaiqbalh.muppi.core.presentation.utils.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.utils.primaryColor
import com.nandaiqbalh.muppi.core.presentation.utils.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.app_name
import muppi.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun IconChip(
	logo: DrawableResource? = null,
	text: String = stringResource(Res.string.app_name),
	modifier: Modifier = Modifier,
) {

	Box(
		modifier = modifier
			.clip(RoundedCornerShape(100))
			.background(primaryBackground)
	) {
		Row(
			modifier = Modifier
				.padding(horizontal = 16.dp, vertical = 8.dp),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {

			if (logo != null) {
				Image(
					painter = painterResource(logo),
					contentDescription = null,
				)
			}

			Spacer(modifier = Modifier.width(8.dp))

			Text(
				text = text,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis,
				modifier = Modifier,
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
					fontSize = 13.sp,
					color = primaryColor,
					textAlign = TextAlign.Start
				)
			)
		}
	}
}

@Composable
fun GenreChip(
	genre: String,
	modifier: Modifier
) {
	Box(
		modifier = modifier
			.clip(RoundedCornerShape(100))
			.background(onBackground.copy(alpha = 0.5f))
			.padding(vertical = 8.dp, horizontal = 16.dp)
	) {
		Text(
			text = genre,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
				fontSize = 10.sp,
				color = primaryColor,
				textAlign = TextAlign.Start
			)
		)
	}
}