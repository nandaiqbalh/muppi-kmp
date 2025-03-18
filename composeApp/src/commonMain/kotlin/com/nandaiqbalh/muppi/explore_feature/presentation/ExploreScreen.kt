package com.nandaiqbalh.muppi.explore_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nandaiqbalh.muppi.core.presentation.utils.primaryFont

@Composable
fun ExploreScreenRoot(

) {
	ExploreScreen()
}

@Composable
fun ExploreScreen(

) {

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryFont),
		contentAlignment = Alignment.Center
	){
		Text(
			"This is Explore Screen"
		)
	}

}