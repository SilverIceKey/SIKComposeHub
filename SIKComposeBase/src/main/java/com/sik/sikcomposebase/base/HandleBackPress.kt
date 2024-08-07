package com.sik.sikcomposebase.base

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

/**
 * 处理返回按钮事件
 */
@Composable
fun HandleBackPress(navController: NavHostController, onBackPressed: () -> Unit) {
    BackHandler(enabled = true) {
        if (!navController.popBackStack()) {
            onBackPressed()
        }
    }
}
