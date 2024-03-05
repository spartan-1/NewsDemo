package com.demo.news.data.remote

import com.demo.news.data.dto.NewsListDto
import retrofit2.http.GET

/**
 * News feed api interface
 *
 */
interface NewsFeedApi {

    /**
     * Get news items
     *
     * @return
     */
    @GET("api.json?rss_url=http://www.abc.net.au/news/feed/51120/rss.xml")
    suspend fun getNewsListDto(): NewsListDto

}