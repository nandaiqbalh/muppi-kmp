package com.nandaiqbalh.muppi.core

import com.nandaiqbalh.muppi.core.domain.DeviceInfo

interface Platform {
    val name: String

    fun getDevicePlatform(): String

    fun isDebugMode(): Boolean

    fun getDeviceInfo(): DeviceInfo

}

expect fun getPlatform(): Platform

