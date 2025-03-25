package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.onBackground
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import com.nandaiqbalh.muppi.core.utils.Constant
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_dialog_error
import muppi.composeapp.generated.resources.ic_dialog_success
import muppi.composeapp.generated.resources.ic_dialog_warning
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


class GeneralDialogState internal constructor() {
	var showDialog by mutableStateOf(false)
	var type by mutableStateOf(Constant.DialogType.ERROR)
	var title by mutableStateOf("")
	var description by mutableStateOf("")
	var buttonDismiss by mutableStateOf("")
	var buttonConfirm by mutableStateOf("")
	var onClickDismiss by mutableStateOf({})
	var onClickConfirm by mutableStateOf({})
}

@Composable
fun rememberGeneralDialogState(key1: Any? = null): GeneralDialogState {
	return remember(key1) {
		GeneralDialogState()
	}
}


@Composable
fun GeneralDialog(
	dialogType: Constant.DialogType,
	title: String = "",
	description: String = "",
	buttonDismiss: String = "",
	buttonConfirm: String = "",
	onClickDismiss: () -> Unit = {},
	onClickConfirm: () -> Unit = {},
) {

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(24.dp))
			.background(primaryFont) // Custom background color
			.padding(16.dp)
			.imePadding() // Adjust for keyboard
	) {
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			Spacer(modifier = Modifier.height(13.dp))

			Image(
				painter = when (dialogType) {
					Constant.DialogType.SUCCESS -> painterResource(Res.drawable.ic_dialog_success)
					Constant.DialogType.WARNING -> painterResource(Res.drawable.ic_dialog_warning)
					else -> painterResource(Res.drawable.ic_dialog_error)
				},
				modifier = Modifier.height(60.dp),
				contentScale = ContentScale.FillHeight,
				contentDescription = "",
			)

			Spacer(modifier = Modifier.height(26.dp))

			Text(
				text = title,
				style = TextStyle(
					fontSize = 20.sp,
					lineHeight = 26.sp,
					fontFamily = FontFamily(Font(Res.font.nunito_semibold)),
					fontWeight = FontWeight.W600,
					color = primaryBackground,
				)
			)

			if (description.isNotEmpty()){
				Spacer(modifier = Modifier.height(8.dp))

				Text(
					text = description,
					style = TextStyle(
						fontSize = 14.sp,
						lineHeight = 18.sp,
						fontFamily = FontFamily(Font(Res.font.nunito_regular)),
						fontWeight = FontWeight.W400,
						color = onBackground,
					)
				)
			}

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				modifier = Modifier
					.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(16.dp)
			) {
				if (buttonDismiss.isNotEmpty())
					Button(
						onClick = {
							onClickDismiss()
						},
						modifier = Modifier.weight(1f).height(48.dp),
						enabled = true,
						shape = RoundedCornerShape(100.dp),
						colors = ButtonDefaults.buttonColors(
							containerColor = primaryFont
						),
						contentPadding = PaddingValues(
							horizontal = 16.dp,
							vertical = 12.dp
						),
						border = BorderStroke(1.dp, primaryColor),
					) {
						Text(
							buttonDismiss,
							maxLines = 1,
							overflow = TextOverflow.Ellipsis,
							style = TextStyle(
								fontSize = 18.sp,
								lineHeight = 24.sp,
								fontFamily = FontFamily(Font(Res.font.nunito_regular)),
								color = primaryColor,
								fontWeight = FontWeight.W600
							)
						)
					}

				if (buttonConfirm.isNotEmpty()) {
					GeneralTextButton(
						text = buttonConfirm,
						modifier = Modifier.weight(1f).height(48.dp),
						textColor = primaryFont,
						isEnable = true,
						onClick = {
							onClickConfirm()
						}
					)
				}
			}
		}
	}
}

@Composable
fun GeneralDialogItemBottom(dialogState: GeneralDialogState) {
	GeneralDialog(
		dialogType = dialogState.type,
		title = dialogState.title,
		description = dialogState.description,
		buttonConfirm = dialogState.buttonConfirm,
		onClickConfirm = dialogState.onClickConfirm,
		buttonDismiss = dialogState.buttonDismiss,
		onClickDismiss = dialogState.onClickDismiss
	)
}

@Composable
fun CustomAnimatedDialog(dialogState: GeneralDialogState) {

	if (dialogState.showDialog) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(Color.Black.copy(alpha = 0.7f)).then(
					if (dialogState.showDialog) {
						Modifier.blur(20.dp, BlurredEdgeTreatment.Unbounded)
					} else {
						Modifier
					},
				)
				.clickable(
					indication = null, // Removes any ripple effect
					interactionSource = remember { MutableInteractionSource() } // Prevents other interactions from being tracked
				) {
					// Action when the background is clicked (if needed)
				}
		)
	}

	// dialog result Code
	AnimatedVisibility(
		visible = dialogState.showDialog,
		enter = slideInVertically(
			initialOffsetY = { it },
			animationSpec = tween(durationMillis = 500)
		) + fadeIn(
			animationSpec = tween(durationMillis = 300) // Control animation duration
		),
		exit = fadeOut(
			animationSpec = tween(durationMillis = 300)
		)
	) {
		Box(
			modifier = Modifier.fillMaxSize().navigationBarsPadding()
				.padding(horizontal = 16.dp, vertical = 16.dp),
			contentAlignment = Alignment.BottomCenter
		) {
			GeneralDialogItemBottom(dialogState)
		}
	}
}

@Composable
fun PreventUserInteractionComponent(
	isPreventUserInteraction: Boolean = false,
	isNeedIndicator: Boolean = true
) {

	if (
		isPreventUserInteraction
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(primaryBackground.copy(0.5f))
				.clickable(
					indication = null, // Removes any ripple effect
					interactionSource = remember { MutableInteractionSource() } // Prevents other interactions from being tracked
				) {
					// Action when the background is clicked (if needed)
				},
			contentAlignment = Alignment.Center
		){
			if (isNeedIndicator){
				PulseAnimation()
			}
		}
	}
}