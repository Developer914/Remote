package com.example.remote.managers.billingManager.prefs

import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject

class BillingPrefs @Inject constructor() {
    private val IS_PREMIUM = "is_premium"

    fun isPremium(): Boolean {
        return Prefs.getBoolean(IS_PREMIUM, false)
    }

    fun saveIsPremium(boolean: Boolean) {
        Prefs.putBoolean(IS_PREMIUM, boolean)
    }

}