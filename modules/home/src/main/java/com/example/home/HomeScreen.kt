package com.example.home

import ChatItem
import ListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    // 使用 NavHost 管理主页与详情页之间的跳转
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomePage(navController) }
        composable("detail/{messageId}") { backStackEntry ->
            // 从 NavBackStackEntry 中获取参数
            val messageId = backStackEntry.arguments?.getString("messageId")
            messageId?.let {
                MessageDetailScreen(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    // 定义微信 Dark 模式背景色
    val weChatDarkBackground = Color(0xFF1F1F1F)
    val weChatDarkSurface = Color(0xFF2C2C2C)
    val weChatTextPrimary = Color(0xFFECECEC)
    val weChatTextSecondary = Color(0xFFAAAAAA)

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val items = listOf(
        ListItem(1, "张三", "你好，最近怎么样？", "10:00"),
        ListItem(2, "李四", "今天下午开会。", "10:30"),
        ListItem(3, "王五", "周末一起出去玩吧！", "11:00"),
        ListItem(4, "赵六", "收到，请确认一下。", "11:30")
    )
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = weChatTextPrimary,
            onPrimary = Color.Black,
            background = weChatDarkBackground,
            surface = weChatDarkSurface,
            onSurface = weChatTextPrimary,
            secondary = weChatTextSecondary,
        )
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                AppBar(
                    title = "微信",
                    onNavigationClick = { /* do something */ },
                    onActionClick = { /* do something */ },
                    scrollBehavior = scrollBehavior
                )
            },
            containerColor = weChatDarkBackground
        ) { innerPadding ->
            ChatList(
                navController = navController,
                innerPadding = innerPadding,
                items = items,
                backgroundColor = weChatDarkSurface,
                textColorPrimary = weChatTextPrimary,
                textColorSecondary = weChatTextSecondary
            )
        }
    }
}

@Composable
fun ChatList(
    navController: NavController,
    innerPadding: PaddingValues,
    items: List<ListItem>,
    backgroundColor: Color,
    textColorPrimary: Color,
    textColorSecondary: Color
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(innerPadding)
    ) {
        items(items) { item ->
            ChatItem(
                imageRes = android.R.drawable.ic_menu_gallery, // 示例图片资源
                name = item.name,
                message = item.message,
                time = item.time,
                textColorPrimary = textColorPrimary,
                textColorSecondary = textColorSecondary,
                onClick = {
                    // 跳转到详情页并传递 messageId 参数
                    navController.navigate("detail/${item.name}")
                }
            )
        }
    }
}

@Composable
fun MessageDetailScreen(messageId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Message Detail for ID: $messageId", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Here are the details for this message...")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()
}