package com.example.remote.ui.screens.remote.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.remote.R
import com.example.remote.util.ext.clickableWithNoRippleEffect


@Composable
fun NavigationWheel() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        RemoteNavigationWheel(
            Modifier
                .aspectRatio(1f / 1f)
        )
    }
}


@Composable
private fun RemoteNavigationWheel(modifier: Modifier) {
    Box(modifier = modifier) {
        NavigationWheelInvisibleButtons(
            Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(0.9f)
                .align(Alignment.Center),
            { },
            { },
            { },
            { },
            {},
        )
        NavigationWheelImage()
    }

}

@Composable
private fun NavigationWheelInvisibleButtons(
    modifier: Modifier,
    onUpClick: () -> Unit,
    onDownClick: () -> Unit,
    onRightClick: () -> Unit,
    onLeftClick: () -> Unit,
    onEnterClick: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // left button
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(0.65f)
                    .clickableWithNoRippleEffect {
                        onLeftClick.invoke()
                    }
            ) {

            }
            // enter button
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(0.4f)
                    .clickableWithNoRippleEffect {
                        onEnterClick.invoke()
                    }
            )

            // right button
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(0.65f)
                    .clickableWithNoRippleEffect {
                        onRightClick.invoke()
                    }
            )
        }

        // up button
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.3f)
                .align(Alignment.TopCenter)
                .clickableWithNoRippleEffect {
                    onUpClick.invoke()
                }
        )

        // down button
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.3f)
                .align(Alignment.BottomCenter)
                .clickableWithNoRippleEffect {
                    onDownClick.invoke()
                }
        )
    }

}

@Composable
private fun NavigationWheelImage() {
    androidx.compose.foundation.Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.ic_wheel),
        contentDescription = "",
        contentScale = ContentScale.FillHeight,
    )
}