package com.sik.sikcomposebase.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun DynamicNavHost(
    startDestination: String = "home",
    navController: NavHostController,
    viewModel: BaseScreenViewModel
) {
    val screens by viewModel.screens.observeAsState(emptyList())

    NavHost(navController = navController, startDestination = startDestination) {
        screens.forEach { screen ->
            composable(screen.route) {
                screen.content()
            }
        }
    }
}