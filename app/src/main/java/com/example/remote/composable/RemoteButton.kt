package com.example.remote.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.R

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import com.example.remote.util.ext.addTouchGestures
import com.example.remote.util.ext.clickableWithNoRippleEffect

@Composable
fun RemoteImageButton(
    modifier: Modifier,
    @DrawableRes drawableIdPressUp: Int,
    @DrawableRes drawableIdPressDown: Int,
    onClick: (() -> Unit)? = null
) {
    val imageDrawable = remember { mutableIntStateOf(drawableIdPressUp) }
    Image(
        modifier = modifier
            .addTouchGestures(
                onPressDown = { imageDrawable.intValue = drawableIdPressDown },
                onPressUp = {
                    imageDrawable.intValue = drawableIdPressUp
                    onClick?.invoke()
                },
                onPressCancel = {
                    imageDrawable.intValue = drawableIdPressUp
                }),
        painter = painterResource(id = imageDrawable.intValue),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
    )
}

@Composable
fun RemoteTextButton(
    modifier: Modifier,
    text: String,
    onClick: (() -> Unit)
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickableWithNoRippleEffect {
                onClick.invoke()
            }
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = R.drawable.bg_btn_num),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier.padding(bottom = 7.dp),
            text = text,
            fontSize = 24.sp,
            fontFamily = Font(R.font.plus_jakarta_sans_bold, FontWeight.Bold).toFontFamily(),
            textAlign = TextAlign.Center
        )
    }

}