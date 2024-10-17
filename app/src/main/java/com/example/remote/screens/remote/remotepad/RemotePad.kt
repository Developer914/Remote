package com.example.remote.ui.screens.remote.remotepad

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.remote.R
import com.example.remote.composable.RemoteImageButton


@Composable
@Preview
fun RemotePad() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val rowModifier = Modifier
            .weight(1f)
            .fillMaxWidth(1f)
        val spacerModifier = Modifier
            .weight(0.1f)
        Spacer(modifier =  Modifier
            .weight(0.4f))
        RemoteImageButtonsRow(
            rowModifier,
            R.drawable.ic_power,
            R.drawable.ic_power,
            R.drawable.ic_hdmi,
            R.drawable.ic_hdmi,
            R.drawable.ic_assistant,
            R.drawable.ic_assistant,
            onBtn1Click = {},
            onBtn2Click = {},
            onBtn3Click = {})
        Spacer(modifier = spacerModifier)
        RemoteImageButtonsRow(
            rowModifier,
            R.drawable.ic_back,
            R.drawable.ic_back,
            R.drawable.ic_keyboard,
            R.drawable.ic_keyboard,
            R.drawable.ic_home,
            R.drawable.ic_home,
            onBtn1Click = {},
            onBtn2Click = {},
            onBtn3Click = {})
        Spacer(modifier = spacerModifier)
        RemoteImageButtonsRow(
            rowModifier,
            R.drawable.ic_vol_down,
            R.drawable.ic_vol_down,
            R.drawable.ic_vol_up,
            R.drawable.ic_vol_up,
            R.drawable.ic_vol_mute,
            R.drawable.ic_vol_mute,
            onBtn1Click = {},
            onBtn2Click = {},
            onBtn3Click = {})
        Spacer(modifier = spacerModifier)
        RemoteImageButtonsRow(
            rowModifier,
            R.drawable.ic_youtube,
            R.drawable.ic_youtube,
            R.drawable.ic_netflix,
            R.drawable.ic_netflix,
            onBtn1Click = {},
            onBtn2Click = {})
        Spacer(modifier = spacerModifier)
    }
}

@Composable
private fun RemoteImageButtonsRow(
    modifier: Modifier,
    @DrawableRes btn1DrawableIdPressUp: Int,
    @DrawableRes btn1DrawableIdPressDown: Int,
    @DrawableRes btn2DrawableIdPressUp: Int,
    @DrawableRes btn2DrawableIdPressDown: Int,
    @DrawableRes btn3DrawableIdPressUp: Int,
    @DrawableRes btn3DrawableIdPressDown: Int,
    onBtn1Click: () -> Unit,
    onBtn2Click: () -> Unit,
    onBtn3Click: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val remoteBtnModifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
        val spacerModifier = Modifier
            .weight(0.3f)
        val edgeSpacerModifier = Modifier
            .weight(0.35f)
        Spacer(modifier = edgeSpacerModifier)
        RemoteImageButton(
            modifier = remoteBtnModifier,
            drawableIdPressUp = btn1DrawableIdPressUp,
            drawableIdPressDown = btn1DrawableIdPressDown,
            onClick = onBtn1Click
        )
        Spacer(modifier = spacerModifier)
        RemoteImageButton(
            modifier = remoteBtnModifier,
            drawableIdPressUp = btn2DrawableIdPressUp,
            drawableIdPressDown = btn2DrawableIdPressDown,
            onClick = onBtn2Click
        )
        Spacer(modifier = spacerModifier)
        RemoteImageButton(
            modifier = remoteBtnModifier,
            drawableIdPressUp = btn3DrawableIdPressUp,
            drawableIdPressDown = btn3DrawableIdPressDown,
            onClick = onBtn3Click
        )
        Spacer(modifier = edgeSpacerModifier)
    }
}

@Composable
private fun RemoteImageButtonsRow(
    modifier: Modifier,
    @DrawableRes btn1DrawableIdPressUp: Int,
    @DrawableRes btn1DrawableIdPressDown: Int,
    @DrawableRes btn2DrawableIdPressUp: Int,
    @DrawableRes btn2DrawableIdPressDown: Int,
    onBtn1Click: () -> Unit,
    onBtn2Click: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val remoteBtnModifier = Modifier
            .weight(1f)
            .aspectRatio(1f)
        val spacerModifier = Modifier
            .weight(0.3f)
        val edgeSpacerModifier = Modifier
            .weight(0.35f)
        Spacer(modifier = edgeSpacerModifier)
        RemoteImageButton(
            modifier = remoteBtnModifier,
            drawableIdPressUp = btn1DrawableIdPressUp,
            drawableIdPressDown = btn1DrawableIdPressDown,
            onClick = onBtn1Click
        )
        Spacer(modifier = spacerModifier)
        Spacer(modifier = remoteBtnModifier)
        Spacer(modifier = spacerModifier)
        RemoteImageButton(
            modifier = remoteBtnModifier,
            drawableIdPressUp = btn2DrawableIdPressUp,
            drawableIdPressDown = btn2DrawableIdPressDown,
            onClick = onBtn2Click
        )
        Spacer(modifier = edgeSpacerModifier)
    }
}