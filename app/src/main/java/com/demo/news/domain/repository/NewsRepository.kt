package com.demo.news.domain.repository

import com.demo.news.data.dto.NewsListDto

/**
 * News repository
 *
 */
interface NewsRepository {

    /**
     * Get news list
     *
     * @return
     */
    suspend fun getNewsListDto(): NewsListDto
}