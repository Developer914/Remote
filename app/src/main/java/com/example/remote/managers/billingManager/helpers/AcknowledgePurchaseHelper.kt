package com.example.remote.managers.billingManager.helpers

import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class AcknowledgePurchaseHelper @Inject constructor() {
    val LOG_TAG = "AcknowledgePurchaseHelper"

    /**
     * Acknowledge a purchase.
     *
     * https://developer.android.com/google/play/billing/billing_library_releases_notes#2_0_acknowledge
     *
     * Apps should acknowledge the purchase after confirming that the purchase token
     * has been associated with a user. This app only acknowledges purchases after
     * successfully receiving the subscription data back from the server.
     *
     * Developers can choose to acknowledge purchases from a server using the
     * Google Play Developer API. The server has direct access to the user database,
     * so using the Google Play Developer API for acknowledgement might be more reliable.
     * If the purchase token is not acknowledged within 3 days,
     * then Google Play will automatically refund and revoke the purchase.
     * This behavior helps ensure that users are not charged for subscriptions unless the
     * user has successfully received access to the content.
     * This eliminates a category of issues where users complain to developers
     * that they paid for something that the app is not giving to them.
     */
    private fun acknowledgePurchase(purchaseToken: String, billingClient: BillingClient?) {
        if (billingClient == null || billingClient.isReady.not()) {
            Log.d(LOG_TAG, "acknowledgePurchase Error: billingClient is Null or Not Ready")
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            val params =
                AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchaseToken).build()
            billingClient.acknowledgePurchase(params) { billingResult ->
                Log.d(LOG_TAG, "acknowledgePurchase: $billingResult")
            }
        }
    }

    /**
     * Acknowledge All Purchases
     */
    fun acknowledgePurchases(purchases: List<Purchase>?, billingClient: BillingClient?) {
        if (purchases == null) {
            Log.d(LOG_TAG, "acknowledgePurchase Error: Purchases is Null")
            return
        }
        if (billingClient == null || billingClient.isReady.not()) {
            Log.d(LOG_TAG, "acknowledgePurchase Error: billingClient is Null or Not Ready")
            return
        }
        purchases.forEach { purchase ->
            try {
                if (isPurchaseAcknowledged(purchase).not()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val purchaseToken = getPurchasedToken(purchase)
                        acknowledgePurchase(purchaseToken, billingClient)
                    }
                }
            } catch (e: Exception) {
                Log.d(LOG_TAG, "acknowledgePurchase Error: $e")
            }
        }

    }

    private fun getPurchasedToken(purchases: Purchase): String {
        return purchases.purchaseToken
    }

    private fun isPurchaseAcknowledged(purchases: Purchase): Boolean {
        return purchases.isAcknowledged
    }
}