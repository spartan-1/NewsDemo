package com.demo.news.data.dto

import com.demo.news.domain.model.NewsItem
import com.demo.news.domain.model.NewsList


/**
 * News list dto
 *
 * @property status
 * @property feed
 * @property items
 *
 */
data class NewsListDto(
    val status: String,
    val feed: Feed,
    val items: List<NewsItemDto>?,

    ) {
    /**
     * Convert NewsList received from api To news list
     *
     * @return
     */
    fun toNewsList(): NewsList {
        val newsList = mutableListOf<NewsItem>()

        items?.forEach {
            newsList.add(it.toNewsItem())
        }
        return NewsList(feed.title ?: "", newsList)
    }
}

/**
 * News item dto
 *
 * @property title
 * @property pubDate
 * @property link
 * @property guid
 * @property author
 * @property thumbnail
 * @property description
 * @property content
 * @property enclosure
 * @property categories
 * @constructor Create empty News item dto
 */
data class NewsItemDto(
    val title: String?,
    val pubDate: String?,
    val link: String?,
    val guid: String?,
    val author: String?,
    val thumbnail: String?,
    val description: String?,
    val content: String?,
    val enclosure: Enclosure?,
    val categories: List<String>
) {
    /**
     * Convert NewsItemDto To news item
     *
     * @return
     */
    fun toNewsItem(): NewsItem = NewsItem(
        enclosure?.link ?: "",
        title ?: "",
        author ?: ""
    )
}

/**
 * Enclosure
 * @property link
 * @property type
 * @property thumbnail
 */
data class Enclosure(
    val link: String?,
    val type: String?,
    val thumbnail: String?
)

/**
 * Feed
 *
 * @property url
 * @property title
 * @property link
 * @property author
 * @property description
 * @property image
 */
data class Feed(
    val url: String?,
    val title: String?,
    val link: String?,
    val author: String?,
    val description: String?,
    val image: String?
)