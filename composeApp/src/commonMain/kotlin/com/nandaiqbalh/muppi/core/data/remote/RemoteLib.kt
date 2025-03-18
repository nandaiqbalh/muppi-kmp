package com.nandaiqbalh.muppi.core.data.remote

import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

//for logging
fun setupKermit(): Logger {
    return Logger(config = Logger.config)
}

//for json serialization
@ExperimentalSerializationApi
fun setupJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
    explicitNulls = false
}

@ExperimentalSerializationApi
fun setupHttpClient(
    baseUrl: String,
    urlProtocol: URLProtocol = URLProtocol.HTTPS,
    kermitLogger: Logger,
    isDebugMode: Boolean = true,
    httpClientProvider: HttpClient
): HttpClient {
    return httpClientProvider.config {
        ContentEncoding()
        expectSuccess = true

        install(ContentNegotiation) {
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }

        defaultRequest {
            host = baseUrl
            url {
                this.user
                protocol = urlProtocol
            }
        }


        install(HttpTimeout) {
            this.requestTimeoutMillis = 600000
            this.connectTimeoutMillis = 600000
            this.socketTimeoutMillis = 600000
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
                level = LogLevel.NONE
            }
        }
    }
}

class CustomLogger(private val log: Logger) : io.ktor.client.plugins.logging.Logger {
    override fun log(message: String) {
        return log.withTag("TAG").d(message)
    }
}