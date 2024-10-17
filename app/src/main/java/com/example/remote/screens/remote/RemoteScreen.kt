package com.example.remote.screens.remote

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remote.composable.RemoteImageButton
import com.example.remote.composable.RemoteNumberButton
import com.example.remote.common.ext.StatBarColor
import com.example.remote.resources.remoteButtonImages
import com.example.remote.resources.remoteButtonNumbers
import com.example.remote.ui.theme.BorderColor
import com.example.remote.ui.theme.SelectedColor
import com.example.remote.ui.theme.TextColor
import com.example.remote.ui.theme.WheelLightGray


@Composable
fun RemoteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        val isRemoteWheelSelected = remember { mutableStateOf(true) }
        val isRemoteSelected = remember { mutableStateOf(true) }

        StatBarColor(Color.White)

        TopButtons(isRemoteSelected.value){
            isRemoteSelected.value = it
        }

        if (isRemoteSelected.value)
            RemoteButtons()
        else
            Keypad()

        if(isRemoteWheelSelected.value)
            Wheel(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        else
            TouchPad(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        MiddleRow(
            isRemoteWheelSelected.value,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ){
            isRemoteWheelSelected.value = it
        }
    }
}

@Composable
fun TopButtons(isRemoteSelected: Boolean , setRemoteSelectedOrNot:(Boolean) -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(.5f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .clickable {
                    setRemoteSelectedOrNot(true)
                }
                .drawBehind {
                    val strokeWidth = 2.dp.toPx() // Border thickness
                    val borderPadding = 0.dp.toPx() // Gap between text and border
                    val y = size.height - strokeWidth / 2 - borderPadding
                    if(isRemoteSelected)
                        drawLine(
                            color = SelectedColor, // Border color
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    else
                    {}
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Remote",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = if(isRemoteSelected) SelectedColor else TextColor
            )
        }

        Box(
            modifier = Modifier
                .weight(.5f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .clickable {
                    setRemoteSelectedOrNot(false)
                }
                .drawBehind {
                    val strokeWidth = 2.dp.toPx() // Border thickness
                    val borderPadding = 0.dp.toPx() // Gap between text and border
                    val y = size.height - strokeWidth / 2 - borderPadding
                    if (!isRemoteSelected)
                        drawLine(
                            color = SelectedColor, // Border color
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    else
                    {}
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Keypad",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = if(isRemoteSelected) TextColor else SelectedColor
            )
        }

    }
}

@Composable
fun RemoteButtons() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(top = 34.dp)// You can adjust this as needed
    ) {
        items(12) { index ->
            if (index != 10) {
                remoteButtonImages[index]?.let { img ->
                    RemoteImageButton(img)
                }
            }
        }
    }
}

@Composable
fun Keypad() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(top = 34.dp)// You can adjust this as needed
    ) {
        items(12) { index ->
            if (index != 9 && index != 11) {
                remoteButtonNumbers[index]?.let { num ->
                    RemoteNumberButton(num.toString())
                }
            }
        }
    }
}

@Composable
fun Wheel(modifier: Modifier){

    Box(
        modifier = modifier
            .size(
                width = 229.dp,
                height = 229.dp
            )
            .border(
                2.dp,
                BorderColor,
                CircleShape
            )
            .clip(CircleShape)
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color.White, WheelLightGray),
                    startX = 0f,
                    endX = 1500f // Adjust as needed for your layout
                )
            ),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .size(
                    width = 85.dp,
                    height = 85.dp
                )
                .border(
                    2.dp,
                    BorderColor,
                    CircleShape
                )
                .clip(CircleShape)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.White, WheelLightGray),
                        startX = 0f,
                        endX = 600f // Adjust as needed for your layout
                    )
                ),
            contentAlignment = Alignment.Center
        ){}
    }

}

@Composable
fun TouchPad(modifier: Modifier){
    Box(
        modifier = modifier
            .size(
                width = 330.dp,
                height = 201.dp
            )
            .border(
                width = 2.dp,
                color = BorderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RectangleShape)
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color.White, WheelLightGray),
                    startX = 0f,
                    endX = 3000f // Adjust as needed for your layout
                )
            ),
        contentAlignment = Alignment.Center
    ){}
}

@Composable
fun MiddleRow(isRemoteWheelSelected : Boolean,
              modifier: Modifier,
              remoteWheelSelectedOrNot:(Boolean) -> Unit){

    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .clickable {
                    remoteWheelSelectedOrNot(true)
                }
        ) {
            Text(
                text = "Remote wheel",
                modifier = Modifier
                    .background(
                        color =
                        if(isRemoteWheelSelected)
                            SelectedColor
                        else
                            Color.White,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    ),
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = if (isRemoteWheelSelected)
                    Color.White
                else
                    TextColor
            )
        }

        Box(
            modifier = Modifier
                .padding(2.dp)
                .clickable {
                    remoteWheelSelectedOrNot(false)
                }
        ) {
            Text(
                text = "Touch pad",
                modifier = Modifier
                    .background(
                        color =
                        if(isRemoteWheelSelected)
                            Color.White
                        else
                            SelectedColor,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    ),
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                color = if (isRemoteWheelSelected)
                    TextColor
                else
                    Color.White
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Preview(){
    RemoteScreen()
}