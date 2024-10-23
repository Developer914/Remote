package com.example.remote.managers.billingManager

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.example.remote.managers.billingManager.enums.PurchaseStatus
import com.example.remote.managers.billingManager.helpers.AcknowledgePurchaseHelper
import com.example.remote.managers.billingManager.helpers.BillingFlowHelper
import com.example.remote.managers.billingManager.prefs.BillingPrefs
import javax.inject.Inject


class GoogleBillingManager @Inject constructor(
    private val appContext: Context,
    private val billingPrefs: BillingPrefs,
    private val acknowledgePurchaseHelper: AcknowledgePurchaseHelper,
    private val billingFlowHelper: BillingFlowHelper
) {


    /**
     * return detail of in app package details
     */
    var subscriptionsProductDetails = MutableLiveData<List<ProductDetails>>()
        private set


    /**
     *  return detail of in app package details
     */
    var inAppProductDetails = MutableLiveData<List<ProductDetails>>()
        private set

    /**
     * Call whenever premium state change. Even in case when new value and last value is same
     */
    var premiumState: MutableLiveData<Boolean> = MutableLiveData(false)
        private set

    private var billingClient: BillingClient? = null

    private val LOG_TAG = "GoogleBillingManager"

    val MONTHLY_SUBSCRIPTION_KEY = "chromecast.remote.monthly"
    val PROMO_MONTHLY_SUBSCRIPTION_KEY = "chromecast.remote.yearlypromo"
    val YEARLY_SUBSCRIPTION_KEY = "chromecast.remote.yearly"
    val LIFETIME_INAPP_KEY = "chromecast.remote.lifetime"

    private val subscriptionKeys = arrayListOf(
        MONTHLY_SUBSCRIPTION_KEY,
        PROMO_MONTHLY_SUBSCRIPTION_KEY,
        YEARLY_SUBSCRIPTION_KEY
    )

    private val inAppKeys = arrayListOf(LIFETIME_INAPP_KEY)

    // in app purchase status for premium state handling
    private var inAppPurchaseStatus: PurchaseStatus = PurchaseStatus.NONE

    // subscription purchase status for premium state handling
    private var subscriptionPurchaseStatus: PurchaseStatus = PurchaseStatus.NONE

    init {
        initBillingClient()
        connectBillingClient()
    }

    private fun initBillingClient() {
        billingClient = BillingClient.newBuilder(appContext)
            .setListener(addPurchaseUpdateListener())
            .enablePendingPurchases()
            .build()
    }

    private fun connectBillingClient() {
        billingClient?.startConnection(addBillingClientStateListener())

    }

    /**
     * Called by the Billing Library when new purchases buy or cancel are detected.
     */
    private fun addPurchaseUpdateListener(): PurchasesUpdatedListener {
        return PurchasesUpdatedListener { billingResult, purchases ->
            Log.d(
                LOG_TAG,
                "PurchasesUpdatedListener: " + "Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
            )
            when (billingResult.responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    if (purchases == null) {
                        Log.w(LOG_TAG, "PurchasesUpdatedListener: null purchase list")
                        queryPurchases()
                    } else {
                        Log.d(
                            LOG_TAG,
                            "PurchasesUpdatedListener: purchase list size ${purchases.size}"
                        )
                        purchases.forEach {
                            if (it.products[0].contains(LIFETIME_INAPP_KEY.lowercase())) {
                                // lifetime in app purchase
                                handleInAppPurchases(purchases)
                            } else {
                                // monthly, yearly subscriptions
                                handleSubscriptionPurchases(purchases)
                            }

                        }
                    }
                }

                BillingClient.BillingResponseCode.USER_CANCELED -> {
                    Log.i(LOG_TAG, "PurchasesUpdatedListener: User canceled the purchase")
                }

                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                    Log.i(LOG_TAG, "PurchasesUpdatedListener: The user already owns this item")
                }

                BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                    Log.e(
                        LOG_TAG,
                        "PurchasesUpdatedListener: Developer error means that Google Play " + "does not recognize the configuration. If you are just getting started, " + "make sure you have configured the application correctly in the " + "Google Play Console. The product ID must match and the APK you " + "are using must be signed with release keys."
                    )
                }
            }
        }
    }

    /**
     * Called when billing client is connecting with google play store
     */
    private fun addBillingClientStateListener(): BillingClientStateListener {
        return object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.d(LOG_TAG, "onBillingSetupFinished: $billingResult")
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    queryProductDetails()
                    queryPurchases()
                }
            }

            override fun onBillingServiceDisconnected() {
                Log.d(LOG_TAG, "onBillingServiceDisconnected:")

                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        }
    }

    fun queryProductDetails() {
        querySubscriptionsProductDetails()
        queryInAppProductDetails()
    }

    /**
     * Fetch Subscriptions Product Details from google play billings
     */
    private fun querySubscriptionsProductDetails() {
        Log.d(LOG_TAG, "querySubscriptionsProductDetails")
        val params = QueryProductDetailsParams.newBuilder()

        val productList: MutableList<QueryProductDetailsParams.Product> = arrayListOf()

        subscriptionKeys.forEach { subscriptionKey ->
            productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(subscriptionKey)
                    .setProductType(BillingClient.ProductType.SUBS).build()
            )
        }
        params.setProductList(productList).let { productDetailsParams ->
            Log.d(LOG_TAG, "querySubscriptionsProductDetailsAsync")
            billingClient?.queryProductDetailsAsync(
                productDetailsParams.build()
            ) { billingResult, productDetailsList ->
                onSubscriptionsProductDetailsResponse(
                    billingResult,
                    productDetailsList
                )
            }
        }

    }

    /**
     * called when queryProductDetails() get Subscriptions Product Details from google play
     */
    private fun onSubscriptionsProductDetailsResponse(
        billingResult: BillingResult, productDetailsList: MutableList<ProductDetails>
    ) {
        Log.d(
            LOG_TAG,
            "onSubscriptionsProductDetailsResponse: " + "Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
        )
        val isResponseOk = billingResult.responseCode == BillingClient.BillingResponseCode.OK

        if (isResponseOk) {
            if (productDetailsList.isNullOrEmpty()) {
                subscriptionsProductDetails.postValue(arrayListOf())
                Log.e(
                    LOG_TAG,
                    "onSubscriptionsProductDetailsResponse: " + "Expected ${subscriptionKeys.size}, " + "Found null ProductDetails. " + "Check to see if the products you requested are correctly published " + "in the Google Play Console."
                )
            } else {
                Log.d(
                    LOG_TAG,
                    "onSubscriptionsProductDetailsResponse: " + "Expected ${subscriptionKeys.size}, " + "Found ${productDetailsList.size} ProductDetails. "
                )
                subscriptionsProductDetails.postValue(productDetailsList)
            }
        }
    }

    /**
     * fetch in app product details from google play billings
     */
    private fun queryInAppProductDetails() {
        Log.d(LOG_TAG, "queryInAppProductDetails")
        val params = QueryProductDetailsParams.newBuilder()

        val productList: MutableList<QueryProductDetailsParams.Product> = arrayListOf()

        inAppKeys.forEach { inAppKey ->
            productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(inAppKey)
                    .setProductType(BillingClient.ProductType.INAPP).build()
            )
        }

        params.setProductList(productList).let { productDetailsParams ->
            Log.d(LOG_TAG, "queryInAppProductDetailsAsync")
            billingClient?.queryProductDetailsAsync(
                productDetailsParams.build()
            ) { billingResult, productDetailsList ->
                onInAppProductDetailsResponse(
                    billingResult,
                    productDetailsList
                )
            }
        }

    }

    /**
     * called when queryInAppProductDetails() get In App Product Details from google play
     */
    private fun onInAppProductDetailsResponse(
        billingResult: BillingResult,
        productDetailsList: MutableList<ProductDetails>
    ) {
        Log.d(
            LOG_TAG,
            "onInAppProductDetailsResponse: " + "Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
        )
        val isResponseOk = billingResult.responseCode == BillingClient.BillingResponseCode.OK

        if (isResponseOk) {
            if (productDetailsList.isNullOrEmpty()) {
                inAppProductDetails.postValue(arrayListOf())
                Log.e(
                    LOG_TAG,
                    "onInAppProductDetailsResponse: " + "Expected ${inAppKeys.size}, " + "Found null ProductDetails. " + "Check to see if the products you requested are correctly published " + "in the Google Play Console."
                )
            } else {
                Log.d(
                    LOG_TAG,
                    "onInAppProductDetailsResponse: " + "Expected ${inAppKeys.size}, " + "Found ${productDetailsList.size} ProductDetails. "
                )
                inAppProductDetails.postValue(productDetailsList)
            }
        }
    }

    /**
     * Query Google Play Billing for existing purchases.
     *
     * New purchases will be provided to the PurchasesUpdatedListener.
     * You still need to check the Google Play Billing API to know when purchase tokens are removed.
     */
    fun queryPurchases() {
        querySubscriptionsPurchases()
        queryInAppPurchases()
    }

    private fun querySubscriptionsPurchases() {
        if (billingClient?.isReady?.not() == true) {
            Log.d(LOG_TAG, "queryProductDetails: BillingClient is not ready")
            return
        }
        subscriptionPurchaseStatus = PurchaseStatus.NONE
        billingClient?.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build()
        ) { billingResult, purchases ->
            Log.d(
                LOG_TAG,
                "querySubscriptionsPurchases: " + "Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
            )
            val isResponseOk = billingResult.responseCode == BillingClient.BillingResponseCode.OK
            if (isResponseOk) {
                handleSubscriptionPurchases(purchases)
            }
        }
    }

    private fun queryInAppPurchases() {
        if (billingClient?.isReady?.not() == true) {
            Log.e(LOG_TAG, "queryInAppPurchases: BillingClient is not ready")
            return
        }
        inAppPurchaseStatus = PurchaseStatus.NONE
        billingClient?.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult, purchases ->
            Log.d(
                LOG_TAG,
                "queryInAppPurchases: " + "Response Code: ${billingResult.responseCode},   Debug Message: ${billingResult.debugMessage}"
            )
            val isResponseOk = billingResult.responseCode == BillingClient.BillingResponseCode.OK
            if (isResponseOk) {
                handleInAppPurchases(purchases)
            }
        }
    }

    private fun handleSubscriptionPurchases(purchases: MutableList<Purchase>?) {
        if (purchases.isNullOrEmpty()) {
            subscriptionPurchaseStatus = PurchaseStatus.NO_PURCHASE
        } else {
            subscriptionPurchaseStatus = PurchaseStatus.PURCHASED
            acknowledgePurchaseHelper.acknowledgePurchases(purchases, billingClient)
        }
        updatePremiumState()
    }

    private fun handleInAppPurchases(purchases: MutableList<Purchase>?) {
        if (purchases.isNullOrEmpty()) {
            inAppPurchaseStatus = PurchaseStatus.NO_PURCHASE
        } else {
            subscriptionPurchaseStatus = PurchaseStatus.PURCHASED
            acknowledgePurchaseHelper.acknowledgePurchases(purchases, billingClient)
        }
        updatePremiumState()
    }

    private fun updatePremiumState() {
        // if any of purchase type found in user google account
        if (inAppPurchaseStatus == PurchaseStatus.PURCHASED || subscriptionPurchaseStatus == PurchaseStatus.PURCHASED) {
            billingPrefs.saveIsPremium(true)
        } else if (inAppPurchaseStatus == PurchaseStatus.NO_PURCHASE && subscriptionPurchaseStatus == PurchaseStatus.NO_PURCHASE) {
            // if both purchase type not found in user google account
            billingPrefs.saveIsPremium(false)
        }
        premiumState.postValue(isUserPremium())
    }


    fun launchSubscriptionBillingFlow(activity: Activity, productDetails: ProductDetails) {
        billingFlowHelper.launchSubscriptionBillingFlow(activity, productDetails, billingClient)
    }

    fun launchInAppBillingFlow(activity: Activity, productDetails: ProductDetails) {
        billingFlowHelper.launchInAppBillingFlow(productDetails, activity, billingClient)
    }

    fun getSubscriptionsOfferPrice(
        productDetail: ProductDetails?
    ): String {
        var maxPrice = "$0.00"
        var highestPricedOffer: ProductDetails.SubscriptionOfferDetails
        var highestPrice = Int.MIN_VALUE
        productDetail?.subscriptionOfferDetails?.let { offerDetails ->
            if (offerDetails.isNotEmpty()) {
                for (offer in offerDetails) {
                    for (price in offer.pricingPhases.pricingPhaseList) {
                        if (price.priceAmountMicros > highestPrice) {
                            highestPrice = price.priceAmountMicros.toInt()
                            highestPricedOffer = offer
                            maxPrice = price.formattedPrice
                        }
                    }
                }
            }
        }
        return removeTrailingZerosFromPrice(maxPrice)
    }

    fun getInAppOfferPrice(
        productDetail: ProductDetails?
    ): String {
        return removeTrailingZerosFromPrice(
            productDetail?.oneTimePurchaseOfferDetails?.formattedPrice ?: "$0.00"
        )
    }

    private fun removeTrailingZerosFromPrice(price: String?): String {
        if (price == null) {
            return ""
        }
        val zerosPattern = ".00"
        if (price.contains(zerosPattern)) {
            return price.replace(zerosPattern, "")
        }
        return price
    }

    fun isSameSubscriptionProductDetail(productId: String): Boolean {
        subscriptionsProductDetails.value?.forEach {
            if (it.productId == productId) {
                return true
            }
        }
        return false
    }

    fun isSameInAppProductDetail(productId: String): Boolean {
        inAppProductDetails.value?.forEach {
            if (it.productId == productId) {
                return true
            }
        }
        return false
    }

    fun getSubscriptionProductDetail(productId: String): ProductDetails? {
        subscriptionsProductDetails.value?.forEach {
            if (it.productId == productId) {
                return it
            }
        }
        return null
    }

    fun getInAppProductDetail(productId: String): ProductDetails? {
        inAppProductDetails.value?.forEach {
            if (it.productId == productId) {
                return it
            }
        }
        return null
    }


    fun isUserPremium(): Boolean {
        return billingPrefs.isPremium()
    }

}