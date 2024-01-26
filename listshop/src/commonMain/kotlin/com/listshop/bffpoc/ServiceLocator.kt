package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.AppAnalytics
import co.touchlab.kmmbridgekickstart.BreedAnalytics
import co.touchlab.kmmbridgekickstart.HttpClientAnalytics
import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bffpoc.repository.BreedRepository
import com.listshop.bffpoc.repository.TagUCP

internal interface ServiceLocator {
    val breedRepository: BreedRepository
    val tagUCP: TagUCP
    val appAnalytics: AppAnalytics
    val breedAnalytics: BreedAnalytics
    val listShopAnalytics: ListShopAnalytics
    val httpClientAnalytics: HttpClientAnalytics
}
