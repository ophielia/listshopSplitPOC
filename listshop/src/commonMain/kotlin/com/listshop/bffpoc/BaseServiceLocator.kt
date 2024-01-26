package com.listshop.bffpoc

import app.cash.sqldelight.db.SqlDriver
import com.listshop.bffpoc.ktor.DogApi
import com.listshop.bffpoc.ktor.DogApiImpl
import com.listshop.bffpoc.ktor.ListShopApi
import com.listshop.bffpoc.ktor.ListShopApiImpl
import com.listshop.bffpoc.repository.BreedRepository
import com.listshop.bffpoc.repository.TagUCP
import co.touchlab.kmmbridgekickstart.AnalyticsHandle
import co.touchlab.kmmbridgekickstart.AppAnalytics
import co.touchlab.kmmbridgekickstart.BreedAnalytics
import co.touchlab.kmmbridgekickstart.HttpClientAnalytics
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.russhwolf.settings.Settings
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock

internal const val SETTINGS_KEY = "KMMBridgeKickStartSettings"
internal const val DB_NAME = "ListshopPOCDb"

internal abstract class BaseServiceLocator(private val analyticsHandle: AnalyticsHandle) :
    ServiceLocator {

    override val breedRepository: BreedRepository by lazy {
        BreedRepository(
            dbHelper = databaseHelper,
            settings = settings,
            dogApi = dogApi,
            clock = Clock.System,
            breedAnalytics = breedAnalytics
        )
    }

    override val tagUCP: TagUCP by lazy {
        TagUCP(
            dbHelper = listShopRepository,
            listshopApi = listShopApi,
            listShopAnalytics = listShopAnalytics
        )
    }


    /*
    class TagUCP internal constructor(
    private val dbHelper: ListShopRepository,
    private val listshopApi: ListShopApi,
    private val listShopAnalytics: ListShopAnalytics
)
     */

    override val appAnalytics: AppAnalytics
        get() = analyticsHandle.appAnalytics

    override val breedAnalytics: BreedAnalytics
        get() = analyticsHandle.breedAnalytics

    override val listShopAnalytics: ListShopAnalytics
        get() = analyticsHandle.listShopAnalytics

    override val httpClientAnalytics: HttpClientAnalytics
        get() = analyticsHandle.httpClientAnalytics

    private val databaseHelper: DatabaseHelper by lazy {
        DatabaseHelper(
            sqlDriver = sqlDriver,
            backgroundDispatcher = Dispatchers.Default,
            breedAnalytics = breedAnalytics
        )
    }

    private val listShopRepository: ListShopRepository by lazy {
        ListShopRepository(
            sqlDriver = sqlDriver,
            listhopAnalytics = listShopAnalytics
        )
    }

    private val dogApi: DogApi by lazy {
        DogApiImpl(engine = clientEngine, httpClientAnalytics = httpClientAnalytics, breedAnalytics = breedAnalytics)
    }

    private val listShopApi: ListShopApi by lazy {
        ListShopApiImpl(engine = clientEngine, httpClientAnalytics = httpClientAnalytics, listShopAnalytics = listShopAnalytics)
    }

    protected abstract val sqlDriver: SqlDriver
    protected abstract val settings: Settings
    protected abstract val clientEngine: HttpClientEngine
}
