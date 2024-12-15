package com.example.mweichat

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.home.HomeScreen
import com.example.home.MessageDetailScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") { HomeScreen(navController) }
        composable("contacts") { Text(text = "contacts") }
        composable("discover") { Text(text = "discover") }
        composable("profile") { Text(text = "profile") }
        composable("detail/{messageId}") { backStackEntry ->
            // 从 NavBackStackEntry 中获取参数
            val messageId = backStackEntry.arguments?.getString("messageId")
            messageId?.let {
                MessageDetailScreen(it)
            }
        }
    }
}

val bottomNavigationItems = listOf(
    BottomNavItem("home", "首页", Icons.Default.Home),
    BottomNavItem("contacts", "通讯录", Icons.Default.Person),
    BottomNavItem("discover", "发现", Icons.Default.Search),
    BottomNavItem("profile", "我的", Icons.Default.Person)
)

val bottomBarRoutes = setOf("home", "contacts", "discover", "profile")