package com.demo.news.domain.model

/**
 * News item
 *
 * @property enclosureLink
 * @property title
 * @property author
 */
data class NewsItem(
    val enclosureLink: String,
    val title: String,
    val author: String
)