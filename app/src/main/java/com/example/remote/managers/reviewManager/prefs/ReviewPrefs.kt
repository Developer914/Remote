package com.afl.chromecast.managers.reviewManager.prefs

import com.pixplicity.easyprefs.library.Prefs
import javax.inject.Inject

class ReviewPrefs @Inject constructor() {

    private val GOOGLE_REVIEW_DIALOG_VISIBILITY = "GoogleReviewDialogVisibility"

    /**
     * Set Month in which google review dialog shown
     */
    fun setMonthInWhichGoogleReviewDialogShown(currentMonth: String) {
        Prefs.putString(GOOGLE_REVIEW_DIALOG_VISIBILITY, currentMonth)
    }

    /**
     * Get month in which last time google review dialog shown
     */
    fun getMonthInWhichLastTimeGoogleReviewDialogShown(): String {
        return Prefs.getString(GOOGLE_REVIEW_DIALOG_VISIBILITY, "")
    }

    /**
     * Check is Google Review Dialog can be shown or not based on last month google review shown or not
     */
    fun shouldShowGoogleReviewDialog(currentMonth: String): Boolean {
        val lastDialogDialogShown = getMonthInWhichLastTimeGoogleReviewDialogShown()
        return lastDialogDialogShown.isBlank() || lastDialogDialogShown != currentMonth
    }

}