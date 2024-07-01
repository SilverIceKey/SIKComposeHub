package com.sik.sikcomposeui.component

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 水平进度指示器
 *
 * @param modifier 修饰符，用于自定义组件外观
 * @param color 进度条颜色
 * @param strokeWidth 进度条宽度
 * @param initialProgress 初始进度值
 * @param targetProgress 目标进度值
 * @param animationSpec 动画规格，可使用 tween 或 keyframes
 * @param startAnimation 是否启动动画
 */
@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier.fillMaxSize(),
    color: Color = Color.Blue,
    strokeWidth: Float = 10f,
    initialProgress: Float = 0f,
    targetProgress: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = tween(
        durationMillis = 2000,
        easing = LinearEasing
    ),
    startAnimation: Boolean = false
) {
    // 使用 remember 保存动画开始状态
    var started by remember { mutableStateOf(startAnimation) }

    // 使用 animateFloatAsState 来处理动画进度
    val progress by animateFloatAsState(
        targetValue = if (started) targetProgress else initialProgress,
        animationSpec = animationSpec,
        label = "animateFloat"
    )

    // 使用 LaunchedEffect 来启动动画
    LaunchedEffect(Unit) {
        started = true
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // 使用 Canvas 来绘制进度条
        Canvas(modifier = modifier) {
            drawLine(
                color = color,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width * progress, size.height / 2),
                strokeWidth = strokeWidth
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = String.format("%.2f%%",progress * 100))
    }

}

/**
 * 垂直进度指示器
 *
 * @param modifier 修饰符，用于自定义组件外观
 * @param color 进度条颜色
 * @param strokeWidth 进度条宽度
 * @param initialProgress 初始进度值
 * @param targetProgress 目标进度值
 * @param animationSpec 动画规格，可使用 tween 或 keyframes
 * @param startAnimation 是否启动动画
 */
@Composable
fun VerticalProgressIndicator(
    modifier: Modifier = Modifier.fillMaxSize(),
    color: Color = Color.Blue,
    strokeWidth: Float = 10f,
    initialProgress: Float = 0f,
    targetProgress: Float = 1f,
    animationSpec: FiniteAnimationSpec<Float> = tween(
        durationMillis = 2000,
        easing = LinearEasing
    ),
    startAnimation: Boolean = false
) {
    // 使用 remember 保存动画开始状态
    var started by remember { mutableStateOf(startAnimation) }

    // 使用 animateFloatAsState 来处理动画进度
    val progress by animateFloatAsState(
        targetValue = if (started) targetProgress else initialProgress,
        animationSpec = animationSpec,
        label = "animateFloat"
    )

    // 使用 LaunchedEffect 来启动动画
    LaunchedEffect(Unit) {
        started = true
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        // 使用 Canvas 来绘制进度条
        Canvas(modifier = modifier) {
            drawLine(
                color = color,
                start = Offset(size.width / 2, size.height),
                end = Offset(size.width / 2, size.height - size.height * progress),
                strokeWidth = strokeWidth
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = String.format("%.2f%%",progress * 100))
    }
}

