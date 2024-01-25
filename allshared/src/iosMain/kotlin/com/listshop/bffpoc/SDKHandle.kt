package com.listshop.bffpoc

import co.touchlab.kmmbridgekickstart.AppAnalytics
import co.touchlab.kmmbridgekickstart.BreedAnalytics
import com.listshop.bffpoc.repository.BreedRepository
import com.listshop.bffpoc.repository.TagUCP

data class SDKHandle(
    val tagUCP: TagUCP,
    val breedRepository: BreedRepository,
    val appAnalytics: AppAnalytics,
    val breedAnalytics: BreedAnalytics
)
