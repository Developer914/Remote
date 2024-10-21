package com.example.remote.screens.remote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.remote.ui.screens.remote.keypad.KeyPad
import com.example.remote.ui.screens.remote.navigation.NavigationWheel
import com.example.remote.ui.screens.remote.remotepad.RemotePad
import com.example.remote.ui.screens.remote.touchpad.Touchpad
import com.example.remote.ui.theme.SelectedBottomNavTextColor
import com.example.remote.ui.theme.TabColor
import com.example.remote.ui.theme.TabTextColor
import com.example.remote.ui.theme.UnselectedBottomNavTextColor
import com.example.remote.util.ext.clickableWithNoRippleEffect
import com.example.remote.R
import kotlinx.coroutines.launch


@Composable
fun RemoteScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.White)) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = R.drawable.bg_app),
            contentDescription = null
        )
        Column(
            Modifier
                .matchParentSize()
        ) {
            PadViewPagerWithTabs(
                Modifier
                    .weight(0.55f)
            )
            WheelViewPagerWithTabs(
                Modifier
                    .weight(0.45f)
            )

        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PadViewPagerWithTabs(
    modifier: Modifier
) {
    val viewPagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        PadTabs(viewPagerState)
        PadViewPager(viewPagerState)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PadViewPager(
    viewPagerState: PagerState
) {

    // move to current page if some issue cause it to stuck between two pages
    LaunchedEffect(viewPagerState.currentPage) {
        viewPagerState.animateScrollToPage(viewPagerState.currentPage)
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        beyondViewportPageCount = 2,
        state = viewPagerState,
        verticalAlignment = Alignment.Top,
    ) { pagerIndex ->
        when (pagerIndex) {
            0 -> {
                RemotePad()
            }

            1 -> {
                KeyPad()
            }

            else -> {

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PadTabs(
    viewPagerState: PagerState,
) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = Modifier.fillMaxWidth(),
        divider = {},
        indicator = { tabPositions ->
            if (viewPagerState.currentPage < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[viewPagerState.currentPage]),
                    color = TabColor

                )
            }
        },
        containerColor = Color.Transparent,
        contentColor = TabColor,
        selectedTabIndex = viewPagerState.currentPage,
    ) {
        Tab(
            viewPagerState.currentPage,
            0,
            "Remote",
            onTabClick = {
                scope.launch {
                    viewPagerState.animateScrollToPage(it)
                }
            })
        Tab(
            viewPagerState.currentPage,
            1,
            "KeyPad",
            onTabClick = {
                scope.launch {
                    viewPagerState.animateScrollToPage(it)
                }
            })
    }
}

@Composable
private fun Tab(
    currentPage: Int,
    tabId: Int,
    text: String,
    onTabClick: (Int) -> Unit,
) {
    val isSelected = currentPage == tabId
    val color =
        if (isSelected) TabTextColor else UnselectedBottomNavTextColor
    Card(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .clickableWithNoRippleEffect {
                onTabClick.invoke(tabId)
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = text,
            color = color,
            fontSize = (16).sp
        )
    }

}

@Composable
private fun WheelViewPagerWithTabs(
    modifier: Modifier
) {
    val viewPagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        WheelViewPager(viewPagerState,Modifier.weight(0.7f))
        WheelTabs(viewPagerState,Modifier.weight(0.3f))

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WheelViewPager(
    viewPagerState: PagerState,
    modifier: Modifier
) {

    // move to current page if some issue cause it to stuck between two pages
    LaunchedEffect(viewPagerState.currentPage) {
        viewPagerState.animateScrollToPage(viewPagerState.currentPage)
    }

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        beyondViewportPageCount = 2,
        state = viewPagerState,
        verticalAlignment = Alignment.Top,
    ) { pagerIndex ->
        when (pagerIndex) {
            0 -> {
                NavigationWheel()
            }

            1 -> {
                Touchpad()
            }

            else -> {

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WheelTabs(
    viewPagerState: PagerState,
    modifier:  Modifier,
) {
    val scope = rememberCoroutineScope()
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Box(modifier.fillMaxWidth(0.65f).padding(top = 10.dp), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.bg_tab_wheel),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            TabRow(
                modifier = Modifier.fillMaxWidth(),
                divider = {},
                indicator = {},
                containerColor = Color.Transparent,
                selectedTabIndex = viewPagerState.currentPage,
            ) {
                WheelItemTab(viewPagerState.currentPage, 0, "Remote Wheel", onTabClick = {
                    scope.launch {
                        viewPagerState.animateScrollToPage(it)
                    }

                })
                WheelItemTab(viewPagerState.currentPage, 1,
                    "Touch pad", onTabClick = {
                        scope.launch {
                            viewPagerState.animateScrollToPage(it)
                        }

                    })
            }
        }

    }


}

@Composable
fun WheelItemTab(currentPage: Int, tabId: Int, text: String, onTabClick: (Int) -> Unit) {
    val isSelected = currentPage == tabId

    val textColor = if (isSelected) Color.White else UnselectedBottomNavTextColor
    val backgroundColor = if (isSelected) SelectedBottomNavTextColor else Color.Transparent
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 3.dp)
            .clickableWithNoRippleEffect {
                onTabClick.invoke(tabId)
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .height(29.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                text = text,
                color = textColor,
                fontSize = 13.sp
            )

        }

    }
}