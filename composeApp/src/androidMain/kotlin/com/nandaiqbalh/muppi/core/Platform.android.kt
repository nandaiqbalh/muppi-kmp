package com.nandaiqbalh.muppi.core

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.nandaiqbalh.muppi.core.domain.DeviceInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidPlatform : Platform, KoinComponent {
	private val context: Context by inject()

	override val name: String = "Android ${Build.VERSION.SDK_INT}"

	override fun getDevicePlatform(): String {
		return "Android-${Build.VERSION.SDK_INT}-${Build.MANUFACTURER} ${Build.MODEL}"
	}

	override fun isDebugMode(): Boolean {
		return true
	}

	override fun getDeviceInfo(): DeviceInfo {
		val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
		val deviceProfile = "${Build.MANUFACTURER} ${Build.MODEL}"
		val deviceOS = "Android ${Build.VERSION.RELEASE}" // or use Build.VERSION_CODES for name

		return DeviceInfo(deviceId, deviceProfile, deviceOS)
	}
}

actual fun getPlatform(): Platform = AndroidPlatform()
