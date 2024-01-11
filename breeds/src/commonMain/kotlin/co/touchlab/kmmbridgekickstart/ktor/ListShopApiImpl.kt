package co.touchlab.kmmbridgekickstart.ktor

import co.touchlab.kmmbridgekickstart.BreedAnalytics
import co.touchlab.kmmbridgekickstart.HttpClientAnalytics
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import co.touchlab.kmmbridgekickstart.data.api.ApiTagLookupEmbedded
import co.touchlab.kmmbridgekickstart.data.model.Tag
import co.touchlab.kmmbridgekickstart.response.BreedResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json

internal class ListShopApiImpl(
    engine: HttpClientEngine,
    private val httpClientAnalytics: HttpClientAnalytics,
    private val listShopAnalytics: ListShopAnalytics
) : ListShopApi {

    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    httpClientAnalytics.logMessage(message)
                }
            }

            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    override suspend fun getAllTags(): List<Tag> {
        val result: ApiTagLookupEmbedded =
            client.get("https://nastyvarmits.fr/api/tag/user").body()

        return result.embeddedList.tagLookupResourceList
            .map { et -> et.embeddedTag }
            .map { at -> Tag.create(at) }
    }
}
