package com.sik.sikcomposebase.base

import androidx.compose.runtime.Composable

data class Screen(
    val route: String,
    val content: @Composable () -> Unit
)