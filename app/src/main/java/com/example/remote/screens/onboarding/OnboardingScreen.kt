package com.example.remote.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.remote.R
import com.example.remote.navigation.NavDestinations
import com.example.remote.util.ext.StatBarColor
import com.example.remote.resources.backgroundImages
import com.example.remote.resources.headings
import com.example.remote.resources.subHeadings
import com.example.remote.ui.theme.OnBoardingButtonTextColor
import com.example.remote.ui.theme.SelectedColor
import java.util.Locale

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val index = remember { mutableIntStateOf(0) }
    Box {
        StatBarColor(Color.White)
        SetBackground(backgroundImages[index.intValue])
        Column {
            MainHeading(
                headings[index.intValue]
            )
            SubHeading(
                subHeadings[index.intValue]
            )
            ContinueButton {
                if (index.intValue == backgroundImages.size - 1) {
                    navController.navigate(NavDestinations.HOME_SCREEN) {
                        popUpTo(NavDestinations.ONBOARDING_SCREEN) {
                            inclusive = true
                        }
                    }
                } else {
                    index.intValue += 1
                }
            }
        }

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
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MainHeading(heading: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 429.dp
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
                top = 14.73.dp,
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
fun ContinueButton(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 102.27.dp
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

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}
