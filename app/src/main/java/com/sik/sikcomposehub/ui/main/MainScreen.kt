package com.sik.sikcomposehub.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sik.sikcomposeui.component.ProgressIndicator

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            navController.navigate("details") {
                launchSingleTop = true
            }
        }) {
            Text("Go to Details")
        }
        ProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = { navController.popBackStack() }) {
                Text("Go to Home")
            }
        }
    }
}