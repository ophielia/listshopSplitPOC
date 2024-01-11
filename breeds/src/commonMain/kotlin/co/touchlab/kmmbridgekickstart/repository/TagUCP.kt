package co.touchlab.kmmbridgekickstart.repository

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import co.touchlab.kmmbridgekickstart.ListShopRepository
import co.touchlab.kmmbridgekickstart.data.model.Tag
import co.touchlab.kmmbridgekickstart.ktor.ListShopApi
import com.russhwolf.settings.Settings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlin.random.Random

class TagUCP internal constructor(
    private val dbHelper: ListShopRepository,
    private val listshopApi: ListShopApi,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class) suspend fun getTags(forceReload: Boolean): List<Tag> {
        val cachedTags = dbHelper.selectAllTags()
        return if (cachedTags.isNotEmpty() && !forceReload) {
            cachedTags
        } else {
            listshopApi.getAllTags().also {
                listShopAnalytics.tagsFetchedFromNetwork(it.size)
                dbHelper.deleteAll()
                dbHelper.insertTags(it)
            }
        }
    }
}


