package com.afl.chromecast.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemBarsColor(statusColor: Color, navColor: Color, shouldDarkBarIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = statusColor, darkIcons = shouldDarkBarIcons)
    systemUiController.setNavigationBarColor(color = navColor, darkIcons = shouldDarkBarIcons)
}

@Composable
fun SetNavigationBarColor(navColor: Color, shouldDarkBarIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(color = navColor, darkIcons = shouldDarkBarIcons)
}

@Composable
fun SetStatusBarColor(statusColor: Color, shouldDarkBarIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = statusColor, darkIcons = shouldDarkBarIcons)
}