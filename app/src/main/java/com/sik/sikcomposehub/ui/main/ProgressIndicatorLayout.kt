package com.sik.sikcomposehub.ui.main

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sik.sikcomposeui.component.ProgressIndicator
import com.sik.sikcomposeui.component.VerticalProgressIndicator


@Composable
fun ProgressIndicatorLayout(navController: NavHostController, contentPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("details") {
                launchSingleTop = true
            }
        }) {
            Text("Go to Details")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            color = Color.Green,
            strokeWidth = 15f,
            initialProgress = 0f,
            targetProgress = 1f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        VerticalProgressIndicator(
            modifier = Modifier
                .width(50.dp)
                .height(200.dp),
            color = Color.Red,
            strokeWidth = 15f,
            initialProgress = 0f,
            targetProgress = 1f,
            animationSpec = keyframes {
                durationMillis = 2000
                0f at 0
                0.5f at 500
                1f at 2000
            }
        )
    }
}