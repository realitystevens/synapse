package com.synapseplus.network

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HttpClientFactory(private val engine: HttpClientEngine, private val tokenProvider: suspend () -> String?) {
    fun create(): HttpClient = HttpClient(engine) {
        install(ContentNegotiation) { 
            json(Json { 
                ignoreUnknownKeys = true
                encodeDefaults = true 
            }) 
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(HttpSend) {
            intercept { request ->
                val token = tokenProvider()
                if (!token.isNullOrBlank()) {
                    request.headers.append(HttpHeaders.Authorization, "Bearer $token")
                }
                execute(request)
            }
        }
        expectSuccess = true
        HttpResponseValidator { 
            validateResponse { resp ->
                if (resp.status.value >= 400) {
                    throw ResponseException(resp, "HTTP ${resp.status}")
                }
            } 
        }
    }
}
