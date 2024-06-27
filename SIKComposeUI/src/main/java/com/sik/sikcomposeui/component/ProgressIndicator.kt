package com.sik.sikcomposeui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color


@Composable
fun ProgressIndicator(modifier: Modifier = Modifier.fillMaxSize()) {
    val progressing by remember {
        mutableStateOf(0f)
    }
    val progress by animateFloatAsState(targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = keyframes {
            durationMillis = 1000
            0.7f at 500
        }))
    Canvas(modifier = modifier) {
        drawLine(
            color = Color.Blue,
            start = Offset(0f, 0f),
            end = Offset(size.width * progressing, 0f),
            strokeWidth = 10f
        )
    }
}