package com.example.remote.ui.screens.remote.touchpad

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.remote.R
import com.example.remote.util.ext.addSwipeGesturesDetector
import com.example.remote.util.ext.clickableWithNoRippleEffect

@Composable
fun Touchpad() {
    Box(
        Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.87f), contentAlignment = Alignment.Center
        ) {
            Touchpad(
                Modifier
                    .fillMaxSize(),
                onSwipeUp = { },
                onSwipeDown = {

                },
                onSwipeLeft = {

                },
                onSwipeRight = {

                },
                onEnterClick = { }
            )
        }
    }
}


@Composable
private fun Touchpad(
    modifier: Modifier,
    onSwipeUp: () -> Unit,
    onSwipeDown: () -> Unit,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    onEnterClick: () -> Unit
) {
    Image(
        modifier = modifier
            .clickableWithNoRippleEffect { onEnterClick.invoke() }
            .addSwipeGesturesDetector(
                { onSwipeUp.invoke() },
                { onSwipeDown.invoke() },
                { onSwipeRight.invoke() },
                { onSwipeLeft.invoke() }),
        painter = painterResource(id = R.drawable.ic_touchpad),
        contentDescription = "",
        contentScale = ContentScale.Fit,
    )
}

