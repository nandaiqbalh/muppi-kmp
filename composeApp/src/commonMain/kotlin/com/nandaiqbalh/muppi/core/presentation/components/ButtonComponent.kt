package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryColor200
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_semibold
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun GeneralTextButton(
	onClick: () -> Unit,
	text: String,
	textColor: Color = primaryFont,
	textSize: Int = 18,
	lineHeight: Int = 24,
	fontWeight: FontWeight = FontWeight.W600,
	fontFamily: FontFamily = FontFamily(Font(Res.font.nunito_semibold)),
	bgColor: Color = primaryColor,
	bgColorPressed: Color = primaryColor200,
	contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
	leftIcon: DrawableResource? = null,
	rightIcon: DrawableResource? = null,
	isEnable: Boolean = true,
	modifier: Modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 16.dp),
) {
	val interactionSource = remember { MutableInteractionSource() }
	val isPressed by interactionSource.collectIsPressedAsState()

	Button(
		modifier = modifier,
		onClick = onClick,
		enabled = isEnable,
		shape = RoundedCornerShape(50),
		colors = ButtonDefaults.buttonColors(
			containerColor = primaryColor,
			contentColor = primaryFont,
		),
		contentPadding = contentPadding,
		interactionSource = interactionSource,
	) {
		if (leftIcon != null) {
			Image(
				painter = painterResource(leftIcon),
				contentDescription = null,
				modifier = Modifier
					.padding(end = 8.dp)
			)
		}
		Text(
			text = text,
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			style = TextStyle(
				fontSize = textSize.sp,
				lineHeight = lineHeight.sp,
				fontWeight = fontWeight,
				fontFamily = fontFamily,
				color = textColor,
				textAlign = TextAlign.Center
			)
		)
		if (rightIcon != null) {
			Image(
				painter = painterResource(rightIcon),
				contentDescription = null,
				modifier = Modifier
					.padding(start = 8.dp)
			)
		}
	}
}