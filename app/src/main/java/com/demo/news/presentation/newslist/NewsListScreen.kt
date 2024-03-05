package com.demo.news.presentation.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.demo.news.presentation.theme.Gray40
import com.demo.news.presentation.theme.PrimaryTextColor
import com.demo.news.presentation.theme.paddingLarge

/**
 * News list screen
 *
 * @param navController
 * @param viewModel
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsListScreen(
    navController: NavController, viewModel: NewsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val pullRefreshState =
        rememberPullRefreshState(state.value.isPullToRefresh, { viewModel.getNewsList() })

    NewsList(pullRefreshState, state.value, state.value.isPullToRefresh, navController)
}

/**
 * News list
 *
 * @param pullRefreshState
 * @param state
 * @param isRefreshing
 *
 * This compose function will draw the news list
 */
@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun NewsList(
    pullRefreshState: PullRefreshState,
    state: NewsListUiState,
    isRefreshing: Boolean,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column {
            Text(
                text = state.newsList.mainTitle,
                textAlign = TextAlign.Center,
                color = PrimaryTextColor,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(paddingLarge)
                    .align(Alignment.Start)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Gray40)
            ) {
                items(state.newsList.newsList) { newsItem ->
                    NewsListItem(newsItem = newsItem, onItemClick = {
                        //todo to implement navigation for next screen
                        //navController.navigate(Screen.NewsDetailScreen.route)
                    })
                }
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        if (state.errorMessage.isNotBlank()) {
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingLarge)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}