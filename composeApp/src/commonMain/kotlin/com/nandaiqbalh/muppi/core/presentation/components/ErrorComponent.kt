package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_failed
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.tv_error_general
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorComponent(
	modifier: Modifier = Modifier,
) {
	Box(
		modifier = modifier,
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
}