package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import kotlinx.coroutines.delay
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_error_general
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.tv_error_general
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorComponent(
	modifier: Modifier = Modifier,
	title: String = stringResource(Res.string.tv_error_general),
	imageModifier: Modifier = Modifier
) {

	var isVisible by remember { mutableStateOf(false) }

	LaunchedEffect(Unit){
		delay(200)
		isVisible = true
	}

	Box(
		modifier = modifier,
		contentAlignment = Alignment.Center
	) {
		// Animated visibility for the Column only (Slide-in from left + Fade-in)
		AnimatedVisibility(
			visible = isVisible, // Always visible but animated on first appearance
			enter = slideInHorizontally(
				initialOffsetX = { -it },  // Start from left side (negative X-axis)
				animationSpec = tween(durationMillis = 2000)  // Control speed
			) + fadeIn(animationSpec = tween(durationMillis = 1500))  // Add fade-in effect
		) {
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				// Error Icon
				Image(
					modifier = imageModifier,
					painter = painterResource(Res.drawable.ic_error_general),
					contentDescription = null,
				)

				Spacer(modifier = Modifier.height(4.dp))

				// Error Text
				Text(
					modifier = Modifier,
					text = title,
					style = TextStyle(
						fontFamily = FontFamily(Font(Res.font.nunito_medium)),
						fontSize = 13.sp,
						color = primaryFont,
						textAlign = TextAlign.Center
					)
				)
			}
		}
	}
}