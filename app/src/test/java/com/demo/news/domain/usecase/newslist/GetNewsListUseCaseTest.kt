package com.demo.news.domain.usecase.newslist

import app.cash.turbine.test
import com.demo.news.BaseTest
import com.demo.news.data.dto.NewsListDto
import com.demo.news.domain.model.NewsItem
import com.demo.news.domain.model.NewsList
import com.demo.news.domain.repository.NewsRepository
import com.demo.news.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetNewsListUseCaseTest : BaseTest() {

    private lateinit var getNewsListUseCase: GetNewsListUseCase

    private val repository: NewsRepository = mockk()
    private val newsListDto: NewsListDto = mockk()
    private val newsList: NewsList = mockk()
    private val newsItem: NewsItem = mockk()

    @BeforeEach
    override fun setUp() {
        super.setUp()
        getNewsListUseCase = GetNewsListUseCase(repository)
    }

    @Test
    fun `get newslist usecase returns success`() = runTest {
        coEvery { newsList.newsList } returns listOf(newsItem)
        coEvery { newsListDto.toNewsList() } returns newsList
        coEvery { repository.getNewsListDto() } returns newsListDto
        getNewsListUseCase.invoke().test {
            awaitItem().apply {
                assertTrue(this is Result.Loading)
            }
            awaitItem().apply {
                assertTrue(this is Result.Success)
                assertTrue((this as Result.Success).data.newsList.isNotEmpty())
            }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `get newslist usecase returns error`() = runTest {
        coEvery { repository.getNewsListDto() } throws (Exception())
        getNewsListUseCase.invoke().test {
            awaitItem().apply {
                assertTrue(this is Result.Loading)
            }
            awaitItem().apply {
                assertTrue(this is Result.Error)
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}