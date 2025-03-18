package com.nandaiqbalh.muppi.core.utils

import com.nandaiqbalh.muppi.core.data.remote.CustomLogger
import com.nandaiqbalh.muppi.core.data.remote.setupKermit
import com.nandaiqbalh.muppi.core.getPlatform

fun emptyString() = ""

fun logging(message: () -> String) {
    if (getPlatform().isDebugMode()) {
        CustomLogger(setupKermit()).log(message.invoke())
    }
}
