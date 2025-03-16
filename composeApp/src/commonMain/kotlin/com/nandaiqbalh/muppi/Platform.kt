package com.nandaiqbalh.muppi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform