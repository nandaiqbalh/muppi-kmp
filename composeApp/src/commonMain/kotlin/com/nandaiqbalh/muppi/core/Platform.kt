package com.nandaiqbalh.muppi.core

import com.nandaiqbalh.muppi.core.domain.DeviceInfo
import io.ktor.client.HttpClient

interface Platform {
    val name: String

    fun getHttpClientEngine(forMultipartData: Boolean = false): HttpClient

    fun getDevicePlatform(): String

    fun isDebugMode(): Boolean

    fun getDeviceInfo(): DeviceInfo

}

expect fun getPlatform(): Platform

