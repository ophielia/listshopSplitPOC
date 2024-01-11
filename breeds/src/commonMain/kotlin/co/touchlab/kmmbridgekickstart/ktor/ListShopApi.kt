package co.touchlab.kmmbridgekickstart.ktor

import co.touchlab.kmmbridgekickstart.data.model.Tag
import co.touchlab.kmmbridgekickstart.response.BreedResult

internal interface ListShopApi {
    suspend fun getAllTags(): List<Tag>
}
