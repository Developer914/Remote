package com.example.remote.ui.screens.remote.keypad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.remote.composable.RemoteTextButton


@Composable
fun KeyPad() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        val rowModifier = Modifier
            .weight(1f)
            .fillMaxWidth(1f)
        val spacerModifier = Modifier
            .weight(0.1f)
        Spacer(
            modifier = Modifier
                .weight(0.4f)
        )
        RemoteNumButtonsRow(
            rowModifier,
            "1",
            "2",
            "3",
            onTextButton1Click = {

            },
            onTextButton2Click = {

            },
            onTextButton3Click = {

            })
        Spacer(modifier = spacerModifier)

        RemoteNumButtonsRow(
            rowModifier,
            "4",
            "5",
            "6",
            onTextButton1Click = {

            },
            onTextButton2Click = {

            },
            onTextButton3Click = {

            })
        Spacer(modifier = spacerModifier)
        RemoteNumButtonsRow(
            rowModifier,
            "7",
            "8",
            "9",
            onTextButton1Click = {
            },
            onTextButton2Click = {
            },
            onTextButton3Click = {
            })
        Spacer(modifier = spacerModifier)
        RemoteCenterNumButton(
            rowModifier,
            "0",
            onTextButtonClick = {
            })
        Spacer(modifier = spacerModifier)
    }

}

@Composable
private fun RemoteNumButtonsRow(
    modifier: Modifier,
    text1: String,
    text2: String,
    text3: String,
    onTextButton1Click: () -> Unit,
    onTextButton2Click: () -> Unit,
    onTextButton3Click: () -> Unit,
) {
    Row(
        modifier, horizontalArrangement = Arrangement.SpaceEvenly,
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
        RemoteTextButton(remoteBtnModifier, text = text1, onClick = {
            onTextButton1Click.invoke()
        })
        Spacer(modifier = spacerModifier)
        RemoteTextButton(remoteBtnModifier, text = text2, onClick = {
            onTextButton2Click.invoke()
        })
        Spacer(modifier = spacerModifier)
        RemoteTextButton(remoteBtnModifier, text = text3, onClick = {
            onTextButton3Click.invoke()
        })
        Spacer(modifier = edgeSpacerModifier)
    }
}

@Composable
private fun RemoteCenterNumButton(
    modifier: Modifier,
    text: String,
    onTextButtonClick: () -> Unit,
) {
    Row(
        modifier, horizontalArrangement = Arrangement.SpaceEvenly,
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
        Spacer(remoteBtnModifier)
        Spacer(modifier = spacerModifier)
        RemoteTextButton(remoteBtnModifier, text = text, onClick = {
            onTextButtonClick.invoke()
        })
        Spacer(modifier = spacerModifier)
        Spacer(remoteBtnModifier)
        Spacer(modifier = edgeSpacerModifier)
    }
}