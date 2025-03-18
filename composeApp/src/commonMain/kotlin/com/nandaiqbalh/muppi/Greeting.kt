package com.nandaiqbalh.muppi

import com.nandaiqbalh.muppi.core.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}