//package com.afl.chromecast.managers.reviewManager
//
//import android.app.Activity
//import com.afl.chromecast.managers.reviewManager.prefs.ReviewPrefs
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class GoogleReviewManager @Inject constructor(
//    private val reviewPrefs: ReviewPrefs,
//) {
//    val TAG = "GoogleReviewManager"
//    private val MONTH_PATTERN = "MM"
//
//    /**
//     * create Review Manager and show google review dialog.
//     * This dialog will not shown everytime when called. Review dialog limit is control by google.
//     * It will show once a month
//     */
//    fun showReviewDialog(activity: Activity?) {
//        if (activity == null) {
//            return
//        }
//        if (reviewPrefs.shouldShowGoogleReviewDialog(getCurrentMonth())) {
//            val manager = ReviewManagerFactory.create(activity)
//            manager.requestReviewFlow().addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Timber.tag(TAG).d("showReviewDialog: Success")
//                    launchGooglePlayReviewDialogFlow(activity, manager, it.result)
//                } else {
//                    Timber.tag(TAG).d("showReviewDialog: Error ${it.exception?.message}")
//                }
//            }
//
//
//        } else {
//            Timber.tag(TAG).d("showReviewDialog: one month is not pass to show dialog again")
//        }
//    }
//
//    /**
//     * open review dialog and listen for its success or failure
//     */
//    private fun launchGooglePlayReviewDialogFlow(
//        activity: Activity,
//        manager: ReviewManager,
//        reviewInfo: ReviewInfo
//    ) {
//        manager.launchReviewFlow(activity, reviewInfo)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    reviewPrefs.setMonthInWhichGoogleReviewDialogShown(getCurrentMonth())
//
//                    Timber.tag(TAG).d("launchGooglePlayReviewDialogFlow: Success")
//                } else {
//                    Timber.tag(TAG)
//                        .d("launchGooglePlayReviewDialogFlow: Error ${task.exception?.message}")
//                }
//            }
//    }
//
//    private fun getCurrentMonth(): String {
//        val dateFormat = SimpleDateFormat(MONTH_PATTERN, Locale.US)
//        val date = Date()
//        return dateFormat.format(date)
//    }
//}