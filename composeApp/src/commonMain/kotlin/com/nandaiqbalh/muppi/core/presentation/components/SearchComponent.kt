package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.onBackground
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_back
import muppi.composeapp.generated.resources.ic_search_field
import muppi.composeapp.generated.resources.nunito_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchCard(
	modifier: Modifier = Modifier,
	placeholder: String,
	onValueChanged: (String) -> Unit,
	onCancelSearch: () -> Unit = {},
) {
	var isFocused by remember { mutableStateOf(false) }
	var textValue by remember { mutableStateOf("") }


	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(6.dp)
	) {
		Box(
			modifier = Modifier.weight(1f).height(48.dp).border(
				border = BorderStroke(
					1.dp, color = when {
						isFocused -> onBackground
						else -> Color.Transparent
					}
				), shape = RoundedCornerShape(size = 16.dp)
			).background(
				color = when {
					isFocused -> onBackground
					else -> Color.Transparent
				}, shape = RoundedCornerShape(size = 16.dp)
			).padding(all = 4.dp)
		) {
			BasicTextField(
				modifier = Modifier.fillMaxWidth().fillMaxHeight().background(
					color = onBackground,
					shape = RoundedCornerShape(size = 16.dp),
				).border(
					shape = RoundedCornerShape(size = 16.dp),
					width = 1.dp,
					color = when {
						isFocused -> onBackground
						else -> onBackground
					},
				).padding(all = 4.dp).onFocusChanged { focusState ->
					isFocused = focusState.isFocused
				},
				textStyle = TextStyle(
					fontSize = 16.sp,
					lineHeight = 22.sp,
					fontFamily = FontFamily(Font(Res.font.nunito_regular)),
					fontWeight = FontWeight.W400,
					color = primaryFont,
				),
				value = textValue,
				onValueChange = { newText ->
					textValue = newText
					onValueChanged(newText)
				},
				decorationBox = { innerTextField ->
					Row(
						modifier = Modifier.padding(horizontal = 8.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						Image(
							modifier = Modifier
								.size(20.dp)
								.clip(CircleShape)
								.clickable {
									onCancelSearch()
								},
							painter = painterResource(Res.drawable.ic_search_field),
							contentDescription = null,
						)
						Spacer(Modifier.width(5.dp))
						Box(
							Modifier.weight(1f)
						) {
							if (textValue.isEmpty()) {
								Text(
									text = placeholder, style = TextStyle(
										fontSize = 16.sp,
										lineHeight = 22.sp,
										fontFamily = FontFamily(Font(Res.font.nunito_regular)),
										fontWeight = FontWeight.W400,
										color = inactiveColor
									)
								)
							}
							innerTextField()
						}
					}
				},

				singleLine = true,
			)
		}

		Image(
			modifier = Modifier.clip(CircleShape)
				.rotate(180f)
				.clickable {
					onCancelSearch()
				},
			painter = painterResource(Res.drawable.ic_back),
			contentDescription = null,
		)
	}
}
