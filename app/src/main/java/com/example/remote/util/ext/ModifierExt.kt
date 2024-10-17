package com.example.remote.util.ext

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs

fun Modifier.clickableWithNoRippleEffect(onClick: (() -> Unit)? = null): Modifier {
    return clickable(
        interactionSource = MutableInteractionSource(),
        indication = null,
    ) { onClick?.invoke() }
}

fun Modifier.linearGradientBackground(firstColor: Color, secondColor: Color): Modifier {
    return background(
        brush = Brush.linearGradient(
            colors = listOf(
                firstColor,
                secondColor
            )
        )
    )
}

fun Modifier.verticalLinearGradientBackground(colors:List<Color>): Modifier {
    return background(
        brush = Brush.verticalGradient(
            colors = colors
        )
    )
}

fun Modifier.linearGradient(firstColor: Color, secondColor: Color): Modifier {
    return background(
        brush = Brush.linearGradient(
            colors = listOf(
                firstColor,
                secondColor
            )
        )
    )
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.addTouchGestures(
    onPressDown: (() -> Unit)? = null,
    onPressUp: (() -> Unit)? = null,
    onPressCancel: (() -> Unit)? = null
): Modifier = composed {
    return@composed Modifier
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = { onPressDown?.invoke() },
                onTap = { onPressUp?.invoke() },
            )
        }
        .pointerInput(Unit) {
            detectDragGestures(
                onDrag = { _, _ -> },
                onDragStart = {},
                onDragEnd = { onPressCancel?.invoke() },
                onDragCancel = { onPressCancel?.invoke() })
        }
}

fun Modifier.addDragsGestureDetector(
    onDragStart: ((offset: Offset) -> Unit)? = null,
    onDragEnd: (() -> Unit)? = null,
    onDragCancel: (() -> Unit)? = null,
    onDrag: ((pointerInputChange: PointerInputChange, offset: Offset) -> Unit)? = null,

    ): Modifier {
    return pointerInput(Unit) {
        detectDragGestures({
            onDragStart?.invoke(it)
        },
            { onDragEnd?.invoke() },
            { onDragCancel?.invoke() },
            { change, dragAmount ->
                change.consume()
                onDrag?.invoke(change, dragAmount)
            })
    }
}

fun Modifier.addSwipeGesturesDetector(
    onSwipeUp: (() -> Unit)? = null,
    onSwipeDown: (() -> Unit)? = null,
    onSwipeRight: (() -> Unit)? = null,
    onSwipeLeft: (() -> Unit)? = null
): Modifier {
    var direction = -1
    return pointerInput(Unit) {
        detectDragGestures(
            onDrag = { change, dragAmount ->
                change.consume()

                val (x, y) = dragAmount
                if (abs(x) > abs(y)) {
                    when {
                        x > 0 -> {
                            direction = 0
                            // right

                        }

                        x < 0 -> {
                            direction = 1
                            // left

                        }
                    }
                } else {
                    when {
                        y > 0 -> {
                            direction = 2
                            // down

                        }

                        y < 0 -> {
                            direction = 3
                            // up

                        }
                    }
                }

            },
            onDragEnd = {
                when (direction) {
                    0 -> {
                        onSwipeRight?.invoke()
                        // right swipe
                    }

                    1 -> {
                        onSwipeLeft?.invoke()
                        // left swipe
                    }

                    2 -> {
                        onSwipeDown?.invoke()
                        // down swipe
                    }

                    3 -> {
                        onSwipeUp?.invoke()
                        // up swipe
                    }
                }
            })
    }
}