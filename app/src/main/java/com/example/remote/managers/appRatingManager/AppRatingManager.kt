//package com.example.remote.managers.appRatingManager
//
//import android.os.Build
//import androidx.fragment.app.DialogFragment
//import androidx.fragment.app.FragmentActivity
//import dagger.hilt.android.scopes.ViewModelScoped
//import javax.inject.Inject
//
//@ViewModelScoped
//class AppRatingManager @Inject constructor() {
//    private val DIALOG_TAG = "appRatingDialog"
//    private val SUBJECT = "Feedback"
//    private val CONTACT_US_EMAIL = "support@newworldtechsol.com"
//    private val ERROR_TOAST_MESSAGE = "Unable to send email"
//
//    private fun buildAppRatingDialog(
//        activity: FragmentActivity
//    ): DialogFragment? {
//        val mailSettings =
//            MailSettings(
//                CONTACT_US_EMAIL,
//                SUBJECT,
//                getDeviceInfo(),
//                ERROR_TOAST_MESSAGE
//            )
//
//        return AppRating.Builder(activity).setCustomTheme(R.style.AlertDialogRating)
//            .setTitleTextId(R.string.rating_title).setMessageTextId(R.string.rating_message)
//            .setMailSettingsForFeedbackDialog(mailSettings)
//            .setMailFeedbackMessageTextId(R.string.email_message)
//            .setStoreRatingTitleTextId(R.string.store_title)
//            .create()
//    }
//
//    private fun getDeviceInfo(): String {
//        return "Device Information\n" + "Device Name: ${Build.MANUFACTURER + " " + Build.MODEL}\n" + "OS Level: ${Build.VERSION.SDK_INT}\n" + "App Version: ${BuildConfig.VERSION_NAME}\n" + "Device ID: ${Build.ID}\n\n"
//    }
//
//    fun showAppRatingDialog(
//        activity: FragmentActivity?
//    ) {
//        if (activity == null) {
//            return
//        }
//        if (activity.supportFragmentManager.findFragmentByTag(DIALOG_TAG) == null) {
//            val appRatingDialog = buildAppRatingDialog(activity)
//            appRatingDialog?.showNow(activity.supportFragmentManager, DIALOG_TAG)
//        }
//    }
//}