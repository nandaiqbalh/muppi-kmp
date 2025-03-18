package com.nandaiqbalh.muppi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import com.nandaiqbalh.muppi.app.App
import com.nandaiqbalh.muppi.core.domain.model.Movie
import com.nandaiqbalh.muppi.home_feature.home.presentation.component.NowPlayingItem
import com.nandaiqbalh.muppi.onboarding_feature.presentation.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()), navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()))

        setContent {
            App()
        }
    }
}

val dummyMovie = Movie(
    adult = false,
    backdropPath = "/gFFqWsjLjRfipKzlzaYPD097FNC.jpg",
    genreIds = listOf(28, 53, 80),
    id = 1126166,
    originalLanguage = "en",
    originalTitle = "Flight Risk",
    overview = "A U.S. Marshal escorts a government witness to trial after he's accused of getting involved with a mob boss, only to discover that the pilot who is transporting them is also a hitman sent to assassinate the informant. After they subdue him, they're forced to fly together after discovering that there are others attempting to eliminate them.",
    popularity = 25.919,
    posterPath = "/q0bCG4NX32iIEsRFZqRtuvzNCyZ.jpg",
    releaseDate = "2025-01-22",
    title = "Flight Risk",
    video = false,
    voteAverage = 6.1234234234,
    voteCount = 469
)

@Preview
@Composable
fun AppAndroidPreview() {
}