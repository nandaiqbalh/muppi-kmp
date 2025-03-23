package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.onBackground
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_movie
import muppi.composeapp.generated.resources.tv_tv_or_series
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilterTypeSection(onSelectType: (Boolean) -> Unit) {
	var selectedType by remember { mutableStateOf(true) }  // Default is "Movie" (true)

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(primaryBackground),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Start
	) {
		// Define a list of types ("Movie" and "TV Series")
		val types = listOf(stringResource(Res.string.tv_movie) to true, stringResource(Res.string.tv_tv_or_series) to false)

		types.forEach { (label, isMovie) ->
			SuggestionChip(
				onClick = {
					if (selectedType != isMovie) {  // Only update if it's not already selected
						selectedType = isMovie
						onSelectType(isMovie)  // Pass true for "Movie" and false for "TV Series"
					}
				},
				modifier = Modifier.height(28.dp),
				label = {
					Text(
						text = label,
						style = TextStyle(
							fontSize = 14.sp,
							lineHeight = 18.sp,
							fontFamily = FontFamily(Font(Res.font.nunito_regular)),
							fontWeight = FontWeight.W500,
							color = if ((selectedType && isMovie) || (!selectedType && !isMovie)) primaryColor else inactiveColor,
							textAlign = TextAlign.Center,
						)
					)
				},
				enabled = true,
				shape = RoundedCornerShape(16.dp),
				border = BorderStroke(
					width = 1.dp,
					color = if ((selectedType && isMovie) || (!selectedType && !isMovie)) primaryColor else inactiveColor.copy(alpha = 0.5f)
				),
				colors = SuggestionChipDefaults.suggestionChipColors(
					containerColor = if ((selectedType && isMovie) || (!selectedType && !isMovie)) primaryColor.copy(alpha = 0.2f) else onBackground,
					labelColor = if ((selectedType && isMovie) || (!selectedType && !isMovie)) primaryColor else inactiveColor
				)
			)

			// Spacer to add some space between chips
			if (isMovie) {
				Spacer(modifier = Modifier.width(8.dp))
			}
		}
	}
}


