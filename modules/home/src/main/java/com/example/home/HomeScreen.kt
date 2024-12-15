package com.example.home

import ChatItem
import ListItem
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val items = listOf(
        ListItem(1, "张三", "你好，最近怎么样？", "10:00"),
        ListItem(2, "李四", "今天下午开会。", "10:30"),
        ListItem(3, "王五", "周末一起出去玩吧！", "11:00"),
        ListItem(4, "赵六", "收到，请确认一下。", "11:30")
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                title = "微信",
                onNavigationClick = { /* do something */ },
                onActionClick = { /* do something */ },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ChatList(
            navController = navController,
            innerPadding = innerPadding,
            items = items
        )
    }
}

@Composable
fun ChatList(
    navController: NavController,
    innerPadding: PaddingValues,
    items: List<ListItem>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(items) { item ->
            ChatItem(
                imageRes = android.R.drawable.ic_menu_gallery, // 示例图片资源
                name = item.name,
                message = item.message,
                time = item.time,
                onClick = {
                    // 跳转到详情页并传递 messageId 参数
                    navController.navigate("detail/${item.name}")
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(navController)
}