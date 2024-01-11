package co.touchlab.kmmbridgekickstart

import app.cash.sqldelight.db.SqlDriver
import co.touchlab.kmmbridgekickstart.data.model.Tag
import co.touchlab.kmmbridgekickstart.db.KMMBridgeKickStartDb
import co.touchlab.kmmbridgekickstart.db.TagLookupEntity

internal class ListShopRepository(
    sqlDriver: SqlDriver,
    private val listhopAnalytics: ListShopAnalytics,
) {
    private val dbRef: KMMBridgeKickStartDb = KMMBridgeKickStartDb(sqlDriver)

    fun selectAllTags(): List<Tag> {
        listhopAnalytics.fetchingTagsFromNetwork()
        val result: List<TagLookupEntity> = dbRef.listShopQueries
            .selectAllTagLookups(::mapTagLookupSelecting).executeAsList()
        return result.map { tle -> Tag.create(tle) }
    }


    suspend fun insertTags(tags: List<Tag>) {
        listhopAnalytics.insertingTagsToDatabase(tags.size)
        dbRef.listShopQueries.transaction {
            tags.forEach { tag ->
                dbRef.listShopQueries.insertIntoTagLookup(
                    tag.externalId,
                    false, tag.name, tag.parentId, "0", tag.tagType, "0"
                )
            }
        }
    }


    suspend fun deleteAll() {
        listhopAnalytics.databaseCleared()
        dbRef.listShopQueries.transaction {
            dbRef.listShopQueries.removeAllTagLookups()
        }
    }

    private fun mapTagLookupSelecting(
        externalId: String?,
        isGroup: Boolean?,
        name: String?,
        parentId: String?,
        power: String?,
        tagType: String?,
        user_id: String?,
    ): TagLookupEntity {
        return TagLookupEntity(
            externalId = externalId,
            isGroup = isGroup == true,
            name = name,
            parentId = parentId,
            power = power,
            tagType = tagType,
            userId = user_id
        )
    }
}
