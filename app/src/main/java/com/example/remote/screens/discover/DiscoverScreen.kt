package com.example.remote.screens.discover

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.R
import com.example.remote.ui.theme.GrayTextColor
import com.example.remote.ui.theme.SelectedColor
import com.example.remote.ui.theme.SemiTransparentBlack
import com.example.remote.ui.theme.SemiTransparentBlack30

@Composable
fun DiscoverScreen() {
    Box(
        modifier = Modifier
            .background(
                color = Color.White
            )
    ) {
        SetBackground()
        Column {
            Heading()
            SearchingForDeviceRow()
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp
                    )
            ) {
                DeviceItem()
                DeviceItem()
                DeviceItem()
            }
        }
    }
}

@Composable
fun SetBackground() {
    Image(
        painterResource(
            R.drawable.bg_discovery
        ),
        "",
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun Heading() {

    Column {
        Text(
            text = "Listing Chromecast\nDevices",
            fontSize = 24.sp,
            fontWeight = FontWeight(800),
            color = SemiTransparentBlack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 27.72.dp
                ),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Text(
            text = "All of your discovered devices on this network \n" +
                    "will be listed here.",
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            color = GrayTextColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 37.dp
                ),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }

}

@Composable
fun SearchingForDeviceRow(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 18.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(22.dp)
                .height(22.dp)
        ) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = GrayTextColor
            )
        }
        Text(
            text = "Searching for devices",
            fontSize = 10.sp,
            fontWeight = FontWeight(400),
            color = GrayTextColor,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 6.dp)
        )
    }
}

@Composable
fun DeviceItem(){
    Column(
        modifier = Modifier
            .padding(
                top = 14.73.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(10.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painterResource(R.drawable.ic_tv),
                "",
                modifier = Modifier
                    .padding(
                        start = 17.2.dp
                    )
            )

            Column(
                modifier = Modifier
                    .padding(
                        start = 15.dp
                    )
            ) {
                Text(
                    text = "avail device",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = SelectedColor,
                    lineHeight = 14.52.sp
                )
                Text(
                    text = "avail device",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier
                        .padding(top = 7.dp),
                    lineHeight = 14.52.sp
                )
                Text(
                    text = "avail device",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = SemiTransparentBlack30,
                    modifier = Modifier
                        .padding(top = 7.dp),
                    lineHeight = 14.52.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    DiscoverScreen()
}