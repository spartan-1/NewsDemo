package com.demo.news.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.news.presentation.newslist.NewsListScreen
import com.demo.news.presentation.theme.NewsDemoTheme
import com.demo.news.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            NewsListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.NewsDetailScreen.route + "/{newsId}"
                        ) {
                            //todo to implement news details screen
                            //NewsDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}