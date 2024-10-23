package com.example.remote.managers.billingManager.helpers

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails
import javax.inject.Inject

class BillingFlowHelper @Inject constructor() {
    val LOG_TAG = "LaunchBillingFlowHelper"

    /**
     * Launching the billing flow.
     *
     * Launching the UI to make a subscription purchase requires a reference to the Activity.
     */
    fun launchSubscriptionBillingFlow(
        activity: Activity,
        productDetails: ProductDetails,
        billingClient: BillingClient?
    ) {
        if (billingClient == null || billingClient.isReady.not()) {
            Log.d(LOG_TAG, "launchSubscriptionBillingFlow: BillingClient is not ready or null")
            return
        }
        productDetails.subscriptionOfferDetails?.let { safeSubscriptionOfferDetails ->
            val offerToken = getSubscriptionOfferToken(safeSubscriptionOfferDetails)
            val billingFlowParams =
                subscriptionBillingFlowParamsBuilder(productDetails, offerToken)

            val billingResult = billingClient.launchBillingFlow(activity, billingFlowParams)
            Log.d(
                LOG_TAG,
                "launchSubscriptionBillingFlow: BillingResponse Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
            )
        }

    }

    /**
     * Launching the billing flow.
     *
     * Launching the UI to make a InApp purchase requires a reference to the Activity.
     */
    fun launchInAppBillingFlow(
        productDetails: ProductDetails,
        activity: Activity,
        billingClient: BillingClient?
    ) {
        if (billingClient == null || billingClient.isReady.not()) {
            Log.d(LOG_TAG, "launchBillingFlow: BillingClient is not ready or null")
            return
        }
        val billingFlowParams =
            inAppBillingFlowParamsBuilder(productDetails)
        val billingResult = billingClient.launchBillingFlow(activity, billingFlowParams)

        Log.d(
            LOG_TAG,
            "launchInAppBillingFlow: BillingResponse Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
        )
    }

    private fun subscriptionBillingFlowParamsBuilder(
        productDetails: ProductDetails, offerToken: String
    ): BillingFlowParams {
        return BillingFlowParams.newBuilder().setProductDetailsParamsList(
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails).setOfferToken(offerToken).build()
            )
        ).build()
    }

    private fun inAppBillingFlowParamsBuilder(productDetails: ProductDetails):
            BillingFlowParams {
        return BillingFlowParams.newBuilder().setProductDetailsParamsList(
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .build()
            )
        ).build()
    }

    private fun getSubscriptionOfferToken(
        offerDetails: List<ProductDetails.SubscriptionOfferDetails>
    ): String {
        return offerDetails.firstOrNull()?.offerToken ?: ""
//        var offerToken = String()
//        var highestPricedOffer: ProductDetails.SubscriptionOfferDetails
//        var highestPrice = Int.MIN_VALUE
//
//        if (!offerDetails.isNullOrEmpty()) {
//            for (offer in offerDetails) {
//                for (price in offer.pricingPhases.pricingPhaseList) {
//                    if (price.priceAmountMicros > highestPrice) {
//                        highestPrice = price.priceAmountMicros.toInt()
//                        highestPricedOffer = offer
//                        offerToken = highestPricedOffer.offerToken
//                    }
//                }
//            }
//        }
//        return offerToken
    }
}