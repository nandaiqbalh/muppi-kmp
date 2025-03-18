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
import androidx.navigation.compose.rememberNavController
import com.nandaiqbalh.muppi.app.App
import com.nandaiqbalh.muppi.app.BottomNavigationBar
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

@Preview
@Composable
fun AppAndroidPreview() {


}