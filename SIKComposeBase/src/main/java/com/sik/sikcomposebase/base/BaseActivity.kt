package com.sik.sikcomposebase.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sik.sikcomposebase.RootScreenViewModel

abstract class BaseActivity : ComponentActivity() {
    protected lateinit var navController: NavHostController
    private lateinit var baseScreenViewModel: RootScreenViewModel
    private var isInitContent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseScreenViewModel = ViewModelProvider(this)[RootScreenViewModel::class.java]
        setContent {
            navController = rememberNavController()
            if (!isInitContent) {
                initContent(navController)
                isInitContent = true
            }
            DynamicNavHost(navController = navController, viewModel = baseScreenViewModel)
        }
    }

    /**
     * 添加场景
     */
    open fun addScreen(screen: Screen) {
        baseScreenViewModel.addScreen(screen)
    }

    /**
     * 移除场景
     */
    open fun removeScreen(route: String) {
        baseScreenViewModel.removeScreen(route)
    }

    /**
     * 初始化内容
     */
    @Composable
    abstract fun initContent(navController: NavHostController)
}