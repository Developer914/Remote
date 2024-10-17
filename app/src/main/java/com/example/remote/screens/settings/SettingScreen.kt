package com.example.remote.screens.settings

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.R
import com.example.remote.util.ext.StatBarColor
import com.example.remote.ui.theme.BlackWith10Opacity
import com.example.remote.ui.theme.CardBackground
import com.example.remote.ui.theme.PremiumYellow
import com.example.remote.ui.theme.TextColor

@Composable
fun PremiumScreen() {
    val ctx = LocalContext.current
    var toastText = remember { "" }
    StatBarColor(Color.White)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        PremiumCard("hello", "hi") {
            Toast.makeText(ctx, it, Toast.LENGTH_SHORT).show()
        }
        Heading("General")
        GeneralSettings()
        Heading("Feedback")
        FeedbackSettings()
        Heading("Others")
        OthersSettings()
    }

}

@Composable
private fun PremiumCard(title: String, subtitle: String, onClick: (String) -> Unit) {

    Spacer(
        modifier = Modifier
            .height(42.73.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .padding(
                horizontal = 25.dp
            )
            .clickable { onClick("Premium Card") }
    ) {
        Image(
            painterResource(R.drawable.premium_card_bg),
            "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painterResource(R.drawable.stars),
            "",
            modifier = Modifier
                .size(
                    width = 75.dp,
                    height = 84.dp
                )
                .offset(
                x = 17.dp,
                y = 15.dp
            )
        )

        Column(
            modifier = Modifier
                .offset(
                    x = 92.dp,
                    y = 20.dp
                )
        ) {
            Text(
                text = "PREMIUM NOW!",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight(600)
            )

            Text(
                text = "Full remote\n" +
                        "access, High \n" +
                        "Quality Casting and No ads.",
                modifier = Modifier
                    .padding(
                        top = 8.dp
                    ),
                color = Color.White,
                fontWeight = FontWeight(300),
                fontSize = 14.sp,
                lineHeight = 16.sp
            )
        }

        Box(
            modifier = Modifier
                .offset(
                    x = 216.dp,
                    y = 47.dp
                )
                .width(77.dp)
                .height(26.dp)
                .background(
                    color = PremiumYellow,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onClick("Upgrade")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Upgrade",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),

            )
        }
    }
}

@Composable
private fun Heading(title: String) {
    Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight(600),
        color = TextColor,
        modifier = Modifier
            .padding(
                start = 34.dp,
                top = 23.dp
            )
    )
}

@Composable
private fun GeneralSettings() {
    Column(
        modifier = Modifier
            .padding(
                start = 25.dp,
                end = 25.dp,
                top = 4.dp
            )
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        RowWithSwitchButton("Vibrate on tap", false)
        Separator()
        RowWithSwitchButton("Sound on tap", true)
    }
}

@Composable
private fun FeedbackSettings() {
    Column(
        modifier = Modifier
            .padding(
                start = 25.dp,
                end = 25.dp,
                top = 4.dp
            )
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        RowWithArrowButton("Chat")
        Separator()
        RowWithArrowButton("Rate & Review")
    }
}

@Composable
private fun OthersSettings() {
    Column(
        modifier = Modifier
            .padding(
                start = 25.dp,
                end = 25.dp,
                top = 4.dp
            )
            .background(
                color = CardBackground,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        RowWithArrowButton("Share")
    }
}

@Composable
fun RowWithSwitchButton(settingName: String, isChecked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 1.dp
            )
            .height(40.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = settingName,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    start = 24.dp
                )
        )

        Switch(
            isChecked,
            onCheckedChange = { false },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    end = 11.dp
                )

        )
    }
}

@Composable
fun RowWithArrowButton(settingName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 1.dp
            )
            .height(40.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = settingName,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    start = 24.dp
                )
        )
        Image(
            painterResource(R.drawable.right_arrow),
            "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(
                    end = 26.dp
                ),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun Separator() {
    Divider(
        color = BlackWith10Opacity,
        thickness = .4.dp,
        modifier = Modifier
            .padding(
                start = 24.dp
            )
    )
}

@Composable
fun ShowToast(str: String) {
    Toast.makeText(LocalContext.current, str, Toast.LENGTH_SHORT).show()
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    PremiumScreen()
}