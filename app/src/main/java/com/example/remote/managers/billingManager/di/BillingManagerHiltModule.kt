package com.example.remote.managers.billingManager.di

import android.content.Context
import com.example.remote.managers.billingManager.GoogleBillingManager
import com.example.remote.managers.billingManager.helpers.AcknowledgePurchaseHelper
import com.example.remote.managers.billingManager.helpers.BillingFlowHelper
import com.example.remote.managers.billingManager.prefs.BillingPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BillingManagerHiltModule {
    @Singleton
    @Provides
    fun provideGoogleBillingsManager(
        @ApplicationContext appContext: Context, billingPref: BillingPrefs , acknowledgePurchaseHelper: AcknowledgePurchaseHelper,billingFlowHelper: BillingFlowHelper
    ): GoogleBillingManager {
        return GoogleBillingManager(appContext, billingPref,acknowledgePurchaseHelper,billingFlowHelper)
    }
}