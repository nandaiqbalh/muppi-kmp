package com.nandaiqbalh.muppi.explore_feature.presentation.explore_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.nandaiqbalh.muppi.core.domain.model.genreList
import com.nandaiqbalh.muppi.core.presentation.inactiveColor
import com.nandaiqbalh.muppi.core.presentation.onBackground
import com.nandaiqbalh.muppi.core.presentation.primaryBackground
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.nunito_regular
import muppi.composeapp.generated.resources.tv_all
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilterGenreSection(
	genres: List<Int>,  // List of selected genre IDs
	onSelectGenres: (List<Int>) -> Unit  // Callback when selection changes
) {
	// State to hold the selected genre IDs
	var selectedGenres by remember { mutableStateOf(genres.toSet()) }

	// Handle chip click logic
	fun toggleGenreSelection(genreId: Int) {
		selectedGenres = if (selectedGenres.contains(genreId)) {
			selectedGenres - genreId  // Deselect the genre
		} else {
			selectedGenres + genreId  // Select the genre
		}
		onSelectGenres(selectedGenres.toList())  // Call the callback with the updated list
	}

	// Handle "All" click to clear the list
	fun selectAll() {
		selectedGenres = setOf()  // Clear all selected genres
		onSelectGenres(selectedGenres.toList())  // Call the callback with an empty list
	}

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.background(primaryBackground)
	) {
		LazyRow(
			modifier = Modifier
				.fillMaxWidth()
				.background(primaryBackground),
			horizontalArrangement = Arrangement.spacedBy(8.dp), // Add space between chips
			verticalAlignment = Alignment.CenterVertically
		) {
			// Add "All" option as the first item
			item {
				SuggestionChip(
					onClick = {
						if (selectedGenres.isNotEmpty()) {
							selectAll()  // Clear all selections when "All" is clicked
						}
						onSelectGenres(selectedGenres.toList())  // Update callback with the new selection
					},
					modifier = Modifier.height(28.dp),
					label = {
						Text(
							text = stringResource(Res.string.tv_all),  // "All" or "Clear All"
							style = TextStyle(
								fontSize = 14.sp,
								lineHeight = 18.sp,
								fontFamily = FontFamily(Font(Res.font.nunito_regular)),
								fontWeight = FontWeight.W500,
								color = if (selectedGenres.isEmpty()) primaryColor else inactiveColor,
								textAlign = TextAlign.Center,
							)
						)
					},
					enabled = true,
					shape = RoundedCornerShape(16.dp),
					border = BorderStroke(1.dp, if (selectedGenres.isEmpty()) primaryColor else inactiveColor.copy(alpha = 0.5f)),
					colors = SuggestionChipDefaults.suggestionChipColors(
						containerColor = if (selectedGenres.isEmpty()) primaryColor.copy(alpha = 0.2f) else onBackground,
						labelColor = if (selectedGenres.isEmpty()) primaryColor else inactiveColor
					)
				)
			}

			// Add the rest of the genres
			items(genreList) { genre ->
				SuggestionChip(
					onClick = {
						toggleGenreSelection(genre.id)  // Toggle genre selection on click
					},
					modifier = Modifier.height(28.dp),
					label = {
						Text(
							text = genre.name,
							style = TextStyle(
								fontSize = 14.sp,
								lineHeight = 18.sp,
								fontFamily = FontFamily(Font(Res.font.nunito_regular)),
								fontWeight = FontWeight.W500,
								color = if (selectedGenres.contains(genre.id)) primaryColor else inactiveColor,
								textAlign = TextAlign.Center,
							)
						)
					},
					enabled = true,
					shape = RoundedCornerShape(16.dp),
					border = BorderStroke(1.dp, if (selectedGenres.contains(genre.id)) primaryColor else inactiveColor.copy(alpha = 0.5f)),
					colors = SuggestionChipDefaults.suggestionChipColors(
						containerColor = if (selectedGenres.contains(genre.id)) primaryColor.copy(alpha = 0.2f) else onBackground,
						labelColor = if (selectedGenres.contains(genre.id)) primaryColor else inactiveColor
					)
				)
			}
		}
	}
}


