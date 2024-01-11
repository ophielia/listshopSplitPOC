package co.touchlab.kmmbridgekickstart.data.model

import co.touchlab.kmmbridgekickstart.data.api.ApiTagLookup
import co.touchlab.kmmbridgekickstart.db.TagLookupEntity

data class Tag(
    val externalId: String?,
    val name: String?,
    val parentId: String?,
    val tagType: String?,
) {
    companion object Factory {
        fun create(apiValue: ApiTagLookup): Tag {
            return Tag(
                apiValue.externalId,
                apiValue.name,
                apiValue.parentId,
                apiValue.tagType
            )
        }

        fun create(dbValue: TagLookupEntity): Tag {
            return Tag(
                dbValue.externalId,
                dbValue.name,
                dbValue.parentId,
                dbValue.tagType
            )
        }
    }

}

