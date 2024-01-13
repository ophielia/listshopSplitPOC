package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository
import co.touchlab.kmmbridgekickstart.repository.TagUCP

data class SDKHandle(
    val tagUCP: TagUCP,
    val breedRepository: BreedRepository,
    val appAnalytics: AppAnalytics,
    val breedAnalytics: BreedAnalytics
)
