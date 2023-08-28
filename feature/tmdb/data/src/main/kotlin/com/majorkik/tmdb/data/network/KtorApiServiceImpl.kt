package com.majorkik.tmdb.data.network

import com.majorkik.tmdb.api.UrlConstants
import com.majorkik.tmdb.data.Config
import com.majorkik.tmdb.data.resource.TV
import com.majorkik.tmdb.data.response.TVDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

internal class KtorApiServiceImpl(engine: HttpClientEngine) : KtorApiService {

    /**
     * Http client
     */
    @OptIn(ExperimentalSerializationApi::class)
    internal val client = HttpClient(engine = engine) {
        install(Logging)

        install(ContentNegotiation) {
            json(
                Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                },
            )
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIME_OUT_MS
            requestTimeoutMillis = TIME_OUT_MS
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)

            url(UrlConstants.BASE_URL) {
                parameters.append("api_key", Config.TMDB_API_KEY)
            }
        }

        install(Resources)
    }

    // region TV
    override suspend fun getTV(tvId: Int): TVDetailsResponse {
        return client.get(TV.Id(id = tvId)).body()
    }
    // endregion

    private companion object {
        private const val TIME_OUT_MS = 5_000L
    }
}
