package com.demo.news.data.repository

import com.demo.news.data.dto.NewsListDto
import com.demo.news.data.remote.NewsFeedApi
import com.demo.news.di.IoDispatcher
import com.demo.news.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * News feed repository impl
 *
 * @property newsFeedApi
 * @property ioDispatcher
 * @constructor Create empty News feed repository impl
 */
class NewsFeedRepositoryImpl @Inject constructor(
    private val newsFeedApi: NewsFeedApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NewsRepository {
    override suspend fun getNewsListDto(): NewsListDto =
        withContext(ioDispatcher) { newsFeedApi.getNewsListDto() }
}