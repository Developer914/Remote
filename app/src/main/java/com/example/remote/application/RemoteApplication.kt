package com.example.remote.application

import android.app.Application
import android.content.ContextWrapper
import android.util.Log
import com.example.remote.managers.billingManager.GoogleBillingManager
import com.pixplicity.easyprefs.library.BuildConfig
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RemoteApplication : Application() {
    /**
     * Don't use this instance. It is only added so google billing can start with app run.
     * Don't remove this.
     */
    @Inject
    lateinit var googleBillingManager: GoogleBillingManager
    override fun onCreate() {
        super.onCreate()
        //enableLogsInDebug()
        configurePreferences()
        //integrateFlurry()
    }

    /**
     * enable timber logs only for debug builds
     */
//    private fun enableLogsInDebug() {
//        if (BuildConfig.DEBUG) {
//            plant(Timber.DebugTree())
//        }
//    }

    /**
     * add easy prefs which make easy to access and use shared preference
     */
    private fun configurePreferences() {
        // Initialize the Prefs class
        Prefs.Builder().setContext(this).setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName).setUseDefaultSharedPreference(true).build()
    }

    /**
     * add flurry to get logs
     */
//    private fun integrateFlurry() {
//        if (BuildConfig.DEBUG.not()) {
//            FlurryAgent.Builder()
//                .withDataSaleOptOut(false) //CCPA - the default value is false
//                .withCaptureUncaughtExceptions(true)
//                .withIncludeBackgroundSessionsInMetrics(true)
//                .withLogLevel(Log.VERBOSE)
//                .withPerformanceMetrics(FlurryPerformance.ALL)
//                .build(this, Constants.FLURRY_KEY)
//        }
//
//    }
}