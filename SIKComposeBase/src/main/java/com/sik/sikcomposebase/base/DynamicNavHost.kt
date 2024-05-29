package com.sik.sikcomposebase.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DynamicNavHost(
    navController: NavHostController,
    viewModel: ScreenViewModel
) {
    val screens by viewModel.screens.observeAsState(emptyList())

    NavHost(navController = navController, startDestination = "home") {
        screens.forEach { screen ->
            composable(screen.route) {
                screen.content()
            }
        }
    }
}