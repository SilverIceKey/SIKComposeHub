package com.sik.sikcomposehub.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.sik.sikcomposebase.base.DynamicNavHost
import com.sik.sikcomposebase.base.HandleBackPress
import com.sik.sikcomposebase.base.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModelStoreOwner: ViewModelStoreOwner) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val contentNavHostController = rememberNavController()
    val mainScreenViewModel = ViewModelProvider(viewModelStoreOwner)[MainScreenViewModel::class]
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(contentNavHostController) { scope.launch { drawerState.close() } }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Compose中台") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.Black
                                )
                            }
                        }
                    )
                }
            ) { contentPadding ->
                HandleBackPress(navController = contentNavHostController) {
                    navController.popBackStack()
                }
                mainScreenViewModel.addScreen(Screen("home") {
                    MainHomeLayout(contentPadding)
                })
                mainScreenViewModel.addScreen(Screen("progressIndicator") {
                    ProgressIndicatorLayout(
                        contentNavHostController,
                        contentPadding
                    )
                })
                mainScreenViewModel.addScreen(Screen("details") {
                    DetailsScreen(contentNavHostController)
                })
                DynamicNavHost(
                    navController = contentNavHostController,
                    viewModel = mainScreenViewModel
                )
            }
        }
    )
}

@Composable
fun DrawerContent(contentNavHostController: NavHostController, onCloseDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("首页", modifier = Modifier
            .fillMaxWidth()
            .clickable {
                contentNavHostController.navigate(
                    "home",
                    navOptions = NavOptions
                        .Builder()
                        .apply {
                            setLaunchSingleTop(true)
                        }
                        .build()
                )
                onCloseDrawer()
            }
            .padding(0.dp, 10.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("进度条", modifier = Modifier
            .fillMaxWidth()
            .clickable {
                contentNavHostController.navigate(
                    "progressIndicator",
                    navOptions = NavOptions
                        .Builder()
                        .apply {
                            setLaunchSingleTop(true)
                        }
                        .build()
                )
                onCloseDrawer()
            }
            .padding(0.dp, 10.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCloseDrawer) {
            Text("关闭侧边栏")
        }
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
