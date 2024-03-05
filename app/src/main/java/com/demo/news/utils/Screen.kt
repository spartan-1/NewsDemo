package com.demo.news.utils

sealed class Screen(val route: String) {
    data object NewsListScreen : Screen("news_list_screen")
    data object NewsDetailScreen : Screen("news_detail_screen")
}