package com.nandaiqbalh.muppi.di

import com.nandaiqbalh.muppi.core.getPlatform
import com.nandaiqbalh.muppi.core.data.remote.setupHttpClient
import com.nandaiqbalh.muppi.core.data.remote.setupJson
import com.nandaiqbalh.muppi.core.data.remote.setupKermit
import io.ktor.http.URLProtocol
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL = "base_url"
const val NETWORK_CLIENT = "shared_network_client"
const val NETWORK_CLIENT_MULTIPART = "shared_network_client_multipart"

@OptIn(ExperimentalSerializationApi::class)
val remoteModule = module {
    single { setupJson() }

    single { setupKermit() }

    single(named(BASE_URL)) { BASE_URL }

    single(named(NETWORK_CLIENT)) {
        setupHttpClient(
            baseUrl = get(named(BASE_URL)),
            urlProtocol = URLProtocol.HTTPS,
            kermitLogger = get(),
            isDebugMode = getPlatform().isDebugMode(),
            httpClientProvider = getPlatform().getHttpClientEngine()
        )
    }

    single(named(NETWORK_CLIENT_MULTIPART)) {
        setupHttpClient(
            baseUrl = get(named(BASE_URL)),
            kermitLogger = get(),
            isDebugMode = getPlatform().isDebugMode(),
            httpClientProvider = getPlatform().getHttpClientEngine(true)
        )
    }
}