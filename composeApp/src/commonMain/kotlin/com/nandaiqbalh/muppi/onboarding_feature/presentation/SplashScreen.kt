package com.nandaiqbalh.muppi.onboarding_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.utils.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.utils.primaryColor
import kotlinx.coroutines.delay
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.app_name
import muppi.composeapp.generated.resources.desc_logo_splash
import muppi.composeapp.generated.resources.iv_logo_splash
import muppi.composeapp.generated.resources.nunito_bold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SplashScreenRoot(
	onCompleteSplash: () -> Unit,
	viewModel: SplashScreenViewModel,
) {

	SplashScreen(
		onAction = { action ->
			when (action) {
				SplashScreenAction.OnCompleteSplashScreen -> {
					onCompleteSplash()
				}
			}

			viewModel.onAction(action)
		}
	)

}

@Composable
fun SplashScreen(
	onAction: (action: SplashScreenAction) -> Unit,
) {

	// demonstrate splash screen for 3 seconds
	LaunchedEffect(Unit) {
		delay(3000)
		onAction(SplashScreenAction.OnCompleteSplashScreen)
	}

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryBackground),
		contentAlignment = Alignment.Center
	) {

		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Image(
				modifier = Modifier
					.fillMaxWidth(),
				painter = painterResource(Res.drawable.iv_logo_splash),
				contentDescription = stringResource(Res.string.desc_logo_splash),
			)

			Spacer(
				modifier = Modifier
					.height(8.dp)
			)

			Text(
				text = stringResource(Res.string.app_name),
				modifier = Modifier
					.fillMaxWidth(),
				style = TextStyle(
					fontFamily = FontFamily(Font(Res.font.nunito_bold)),
					fontSize = 31.sp,
					color = primaryColor,
					textAlign = TextAlign.Center
				)
			)
		}
	}
}