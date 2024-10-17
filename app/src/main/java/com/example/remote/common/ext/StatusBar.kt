package com.example.remote.common.ext


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatBarColor(mColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = mColor)
}