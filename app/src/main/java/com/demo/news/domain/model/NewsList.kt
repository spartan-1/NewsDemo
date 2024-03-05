package com.demo.news.domain.model

/**
 * News list
 *
 * @property mainTitle
 * @property newsList
 *
 */
data class NewsList(
    val mainTitle: String,
    val newsList: List<NewsItem>
)