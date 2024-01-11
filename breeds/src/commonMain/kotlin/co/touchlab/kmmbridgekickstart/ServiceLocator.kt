package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository
import co.touchlab.kmmbridgekickstart.repository.TagUCP

internal interface ServiceLocator {
    val breedRepository: BreedRepository
    val tagUCP: TagUCP
    val appAnalytics: AppAnalytics
    val breedAnalytics: BreedAnalytics
    val listShopAnalytics: ListShopAnalytics
    val httpClientAnalytics: HttpClientAnalytics
}
