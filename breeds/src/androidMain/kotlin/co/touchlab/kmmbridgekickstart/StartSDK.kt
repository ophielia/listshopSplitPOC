package co.touchlab.kmmbridgekickstart

import android.content.Context
import co.touchlab.kmmbridgekickstart.repository.TagUCP

fun startSDK(analytics: Analytics, context: Context): SDKHandle {
    val analyticsHandle = initAnalytics(analytics)
    val breedRepository = breedStartup(context, analyticsHandle)
    val tagUCP = tagUCPStartup(context, analyticsHandle)
    return SDKHandle(
        breedRepository = breedRepository,
        appAnalytics = analyticsHandle.appAnalytics,
        breedAnalytics = analyticsHandle.breedAnalytics,
        tagUCP = tagUCP
    )
}