package com.example.remote.screens.settings.premium

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.R
import com.example.remote.ui.theme.OnBoardingButtonTextColor
import com.example.remote.ui.theme.PackageItemBgColor
import com.example.remote.ui.theme.SelectedColor
import com.example.remote.ui.theme.TabColor
import com.example.remote.util.ext.StatBarColor
import java.util.Locale

@Composable
fun PremiumScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        StatBarColor(Color.White)
        SetBackground(R.drawable.bg_premium)
        Column {
            MainHeading(
                "Premium Features"
            )
            SubHeading(
                "Unlock all remote buttons\n" +
                        "No limits on Touchpad\n" +
                        "No irritating Ads"
            )

            Packages()
            ContinueButton {

            }
        }
        Links(
            Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SetBackground(drawable: Int) {
    Image(
        painterResource(
            drawable
        ),
        "",
        modifier = Modifier
            .fillMaxWidth()
            .height(257.dp),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MainHeading(heading: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 247.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = heading.uppercase(Locale.getDefault()),
            fontSize = 24.sp,
            fontWeight = FontWeight(800),
            color = SelectedColor,
            textAlign = TextAlign.Center,
            lineHeight = 27.68.sp
        )
    }
}

@Composable
fun SubHeading(subHeading: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 18.dp,
                start = 25.dp,
                end = 25.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = subHeading,
            fontSize = 18.sp,
            fontWeight = FontWeight(400),
            textAlign = TextAlign.Center,
            lineHeight = 24.51.sp
        )
    }
}

@Composable
fun Packages() {
    val subscription = remember { mutableStateOf(Subscription.YEARLY) }
    Column(
        modifier = Modifier
            .padding(
                top = 18.dp,
                start = 21.6.dp,
                end = 21.6.dp
            )
    ) {
        PackageItem("$9.99","Monthly", "Discounted Price", subscription.value == Subscription.MONTHLY){
            subscription.value = Subscription.MONTHLY
        }
        Spacer(Modifier.height(18.dp))
        PackageItem("$34.99","Yearly", "3 days free trial",subscription.value == Subscription.YEARLY){
            subscription.value = Subscription.YEARLY
        }
        Spacer(Modifier.height(18.dp))
        PackageItem("$69.99","LifeTime", "One Time Payment",subscription.value == Subscription.LIFETIME){
            subscription.value = Subscription.LIFETIME
        }
    }
}

@Composable
fun PackageItem(
    price: String,
    subscriptionType: String,
    subscriptionDetails: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(66.dp)
                .background(
                    color = PackageItemBgColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .clickable{
                    onClick()
                }
                .align(Alignment.BottomEnd)
                .then(
                    if (isSelected) {
                        Modifier.border(
                            width = 2.dp, // Border thickness
                            color = TabColor, // Border color for selected item
                            shape = RoundedCornerShape(16.dp) // Matching the background shape
                        )
                    } else Modifier // No border if not selected
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(.54f)
                    .align(Alignment.CenterVertically)
                    .padding(
                        start = 25.dp
                    )
            ) {
                Text(
                    text = subscriptionType,
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = price,
                    modifier = Modifier
                        .padding(
                            top = 3.dp
                        ),
                    fontSize = 15.sp
                )
            }
            Row(
                modifier = Modifier
                    .weight(.46f)
                    .align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = subscriptionDetails,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Box(
                    modifier = Modifier
                        .padding(
                            end = 22.8.dp
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painterResource(R.drawable.empty_bubble),
                        "",
                    )
                    if (isSelected)
                    Image(
                        painterResource(R.drawable.filled_bubble),
                        "",
                    )
                }
            }
        }

        if (isSelected)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    end = 73.dp
                )
                .size(
                    width = 92.dp,
                    height = 18.dp
                )
                .background(
                    color = TabColor,
                    shape = RoundedCornerShape(6.dp)
                )
        ){}
    }

}


@Composable
fun ContinueButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 33.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(R.drawable.ic_bg_button),
                "",
                modifier = Modifier
                    .clip(RoundedCornerShape(13.dp))
                    .clickable {
                        onClick()
                    }
            )

            Text(
                text = "Continue",
                fontSize = 17.sp,
                fontWeight = FontWeight(700),
                color = OnBoardingButtonTextColor
            )
        }
    }
}

@Composable
fun Links(modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(
                start = 5.dp,
                end = 5.dp,
                bottom = 15.dp
            )
    ) {
        Text(
            text = "Privacy Policy",
            modifier = Modifier
                .weight(.33f)
        )
        Text(
            text = "Terms of use",
            modifier = Modifier
                .weight(.33f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Subscription info",
            modifier = Modifier
                .weight(.33f),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun OutLine(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(
                width = 92.dp,
                height = 18.dp
            )
            .background(
                Color.Blue,
                shape = RoundedCornerShape(6.dp)
            )
    ){}
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    PremiumScreen()
}