package com.nandaiqbalh.muppi.home_feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nandaiqbalh.muppi.core.presentation.utils.primaryBackground

@Composable
fun HomeScreenRoot(

) {
	HomeScreen()
}

@Composable
fun HomeScreen(

) {

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(primaryBackground)
	)

}