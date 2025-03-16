package com.nandaiqbalh.muppi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.nandaiqbalh.muppi.di.initKoin
import org.koin.android.ext.koin.androidContext

class MuppiApplication : Application() {

	override fun onCreate() {
		super.onCreate()

		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

		initKoin {
			androidContext(this@MuppiApplication)
		}
	}
}