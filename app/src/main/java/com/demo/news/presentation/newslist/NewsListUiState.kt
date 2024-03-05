package com.demo.news.presentation.newslist

import com.demo.news.domain.model.NewsList


/**
 * News list ui state
 *
 * @property isLoading
 * @property isPullToRefresh
 * @property newsList
 * @property errorMessage
 *
 */
data class NewsListUiState(
    val isLoading: Boolean = false,
    val isPullToRefresh: Boolean = false,
    val newsList: NewsList = NewsList("", emptyList()),
    val errorMessage: String = ""
)