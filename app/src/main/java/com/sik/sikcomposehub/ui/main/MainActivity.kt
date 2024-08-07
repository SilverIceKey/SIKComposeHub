package com.sik.sikcomposehub.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sik.sikcomposebase.base.BaseActivity
import com.sik.sikcomposebase.base.Screen

class MainActivity : BaseActivity() {
    @Composable
    override fun initContent(navController: NavHostController) {
        addScreen(Screen(route = "home"){HomeScreen(navController = navController,this)})
    }

}