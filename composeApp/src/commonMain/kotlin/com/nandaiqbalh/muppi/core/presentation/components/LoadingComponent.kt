package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.nandaiqbalh.muppi.core.presentation.gray100
import com.nandaiqbalh.muppi.core.presentation.gray300
import com.nandaiqbalh.muppi.core.presentation.gray50
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import muppi.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

fun Modifier.shimmerBackground(shape: Shape = RectangleShape): Modifier = composed {
	val transition = rememberInfiniteTransition()
	val translateAnimation by transition.animateFloat(
		initialValue = 0f,
		targetValue = 400f,
		animationSpec = infiniteRepeatable(
			tween(durationMillis = 1500, easing = LinearOutSlowInEasing),
			RepeatMode.Restart
		),
	)
	val shimmerColors = listOf(
		gray50,
		gray100,
		gray300,
	)
	val brush = Brush.linearGradient(
		colors = shimmerColors,
		start = Offset(translateAnimation, translateAnimation),
		end = Offset(translateAnimation + 100f, translateAnimation + 100f),
		tileMode = TileMode.Mirror,
	)
	return@composed this.then(background(brush, shape))
}

@Composable
fun PulseAnimation(modifier: Modifier = Modifier) {
	val transition = rememberInfiniteTransition()
	val progress by transition.animateFloat(
		initialValue = 0f,
		targetValue = 1f,
		animationSpec = infiniteRepeatable(
			animation = tween(1000),
			repeatMode = RepeatMode.Restart
		)
	)

	Box(
		modifier = modifier,
		contentAlignment = Alignment.Center
	){
		Box(
			modifier = Modifier.size(40.dp)
				.graphicsLayer {
					scaleX = progress
					scaleY = progress
					alpha = 1f - progress
				}
				.border(
					width = 5.dp,
					color = primaryColor,
					shape = CircleShape
				)
		)
	}

}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoadingNextPageComponent(modifier: Modifier = Modifier) {

	val composition by rememberLottieComposition {
		LottieCompositionSpec.JsonString(
			Res.readBytes("files/loading.json").decodeToString()
		)
	}

	Column(
		modifier = modifier
			.fillMaxWidth()
	){
		Spacer(
			modifier = Modifier
				.height(8.dp)
		)

		Image(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			painter = rememberLottiePainter(
				composition = composition,
				iterations = Compottie.IterateForever
			),
			contentDescription = "Lottie animation"
		)

	}
}