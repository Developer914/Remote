package com.chromecastremote.util.ext

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@SuppressLint("RestrictedApi")
fun NavHostController.isDestinationExistInBackStack(route: String): Boolean {
    var isDestinationInBackStack = false
    currentBackStack.value.forEach {
        if (route == it.destination.route) {
            isDestinationInBackStack = true
        }
    }
    return isDestinationInBackStack
}

fun NavHostController.navigateIfNotExistInDestinationBackStack(route: String) {
    if (isDestinationExistInBackStack(route)) {
        return
    }
    navigate(route)
}
@Composable
fun NavController.rememberCurrentRoute(): String? {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}