package com.chromecastremote.util.ext

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.provider.Settings
import androidx.annotation.ColorRes

/**
 * Change navigation bar color
 */
fun Activity.changeNavigationBarColor(color: Int) {
    window.navigationBarColor = getColor(color)
}

/**
 * Change status bar color
 */
fun Activity.changeStatusBarColor(@ColorRes colorResId: Int) {
    window.statusBarColor = resources.getColor(colorResId, null)
}
