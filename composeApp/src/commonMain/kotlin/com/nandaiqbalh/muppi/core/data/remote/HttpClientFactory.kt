package com.nandaiqbalh.muppi.core.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as KermitLogger

object HttpClientFactory {

    fun create(
        engine: HttpClientEngine,
        isDebugMode: Boolean = false,
        kermitLogger: KermitLogger
    ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(json = Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    explicitNulls = false
                }, contentType = ContentType.Any)
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 20_000
                requestTimeoutMillis = 20_000
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    when (cause) {
                        is ServerResponseException -> {
                            val serverResponseResponse = cause.response
                            val serverResponseExceptionText = serverResponseResponse.bodyAsText().trimIndent()
                            throw ApiException(httpCode = cause.response.status.value, errorMessage = serverResponseExceptionText)
                        }

                        is ClientRequestException -> {
                            throw ApiException(httpCode = cause.response.status.value, errorMessage = cause.toString())
                        }

                        else -> {
                            throw cause
                        }
                    }
                }
            }

            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }

            if (isDebugMode) {
                install(Logging) {
                    logger = CustomLogger(kermitLogger)
                    level = LogLevel.ALL
                }
            }
        }
    }
}

//for logging
fun setupKermit(): KermitLogger {
    return KermitLogger(config = co.touchlab.kermit.Logger.config)
}

class CustomLogger(private val log: KermitLogger) : io.ktor.client.plugins.logging.Logger {
    override fun log(message: String) {
        return log.withTag("TAG").d(message)
    }
}