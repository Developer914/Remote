package com.example.remote.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.R

@Composable
fun RemoteImageButton(img: Int) {
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.bg_button),
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(29.dp))
                .clickable { }
        )

        Image(
            painter = painterResource(img),
            contentDescription = ""
        )
    }
}

@Composable
fun RemoteNumberButton(num: String) {
    Box(
        modifier = Modifier
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.bg_button),
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(29.dp))
                .clickable { }
        )

        Text(
            text = num,
            fontSize = 24.sp,
            fontWeight = FontWeight(700),
            color = Color.Black

        )
    }
}