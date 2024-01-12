package core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun ktorHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            useAlternativeNames = false
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    defaultRequest {
        header("Accept", "application/json")
        header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MzM4MGU4YzNjYTk4Y2RmMzdmMWZmZjU5Mjg2MTdlOSIsInN1YiI6IjYxZDc4ZjRiNjg0MGNjMDA5ZDJhYzM2NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Xu8LOECyNTYpIJorWkDBzDxEsnynd9Xal5Iyro0u46E")
        url("https://api.themoviedb.org")
        url {
            protocol = URLProtocol.HTTPS
        }
    }
}