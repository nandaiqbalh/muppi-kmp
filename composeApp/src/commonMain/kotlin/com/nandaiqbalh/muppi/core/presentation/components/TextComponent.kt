package com.nandaiqbalh.muppi.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.inactiveDarkColor
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_medium
import muppi.composeapp.generated.resources.nunito_regular
import org.jetbrains.compose.resources.Font

@Composable
fun HeaderText(
	title: String,
	actionText: String,
	onActionClick: () -> Unit,
) {

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		horizontalArrangement = Arrangement.SpaceBetween
	) {

		Text(
			text = title,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_medium)),
				fontSize = 20.sp,
				color = primaryFont,
			)
		)

		Text(
			modifier = Modifier
				.clickable {
					onActionClick()
				},
			text = actionText,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_medium)),
				fontSize = 13.sp,
				color = primaryColor,
			)
		)


	}
}

@Composable
fun SingleHeaderText(
	title: String,
) {

	Text(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		maxLines = 1,
		overflow = TextOverflow.Ellipsis,
		text = title,
		style = TextStyle(
			fontFamily = FontFamily(Font(Res.font.nunito_medium)),
			fontSize = 20.sp,
			color = primaryFont,
			textAlign = TextAlign.Start
		)
	)
}

@Composable
fun TitleValueText(
	title: String,
	value: String,
) {

	Column(
		modifier = Modifier
			.fillMaxWidth()
	) {

		Text(
			text = title,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_regular)),
				fontSize = 10.sp,
				color = inactiveDarkColor,
			)
		)

		Text(
			modifier = Modifier,
			text = value,
			style = TextStyle(
				fontFamily = FontFamily(Font(Res.font.nunito_regular)),
				fontSize = 16.sp,
				color = primaryFont,
			)
		)
	}
}