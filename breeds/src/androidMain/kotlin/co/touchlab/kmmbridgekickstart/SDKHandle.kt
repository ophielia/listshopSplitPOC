package co.touchlab.kmmbridgekickstart

import co.touchlab.kmmbridgekickstart.repository.BreedRepository
import co.touchlab.kmmbridgekickstart.repository.TagUCP

data class SDKHandle(
    val breedRepository: BreedRepository,
    val tagUCP: TagUCP,
    val appAnalytics: AppAnalytics,
    val breedAnalytics: BreedAnalytics
)