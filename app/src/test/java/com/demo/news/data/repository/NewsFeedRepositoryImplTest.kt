package com.demo.news.data.repository

import com.demo.news.BaseTest
import com.demo.news.data.dto.NewsListDto
import com.demo.news.data.remote.NewsFeedApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NewsFeedRepositoryImplTest : BaseTest() {

    private lateinit var newsFeedRepository: NewsFeedRepositoryImpl
    private val newsFeedApi: NewsFeedApi = mockk()
    private val newsListDto: NewsListDto = mockk()

    @BeforeEach
    override fun setUp() {
        super.setUp()
        newsFeedRepository = NewsFeedRepositoryImpl(newsFeedApi, StandardTestDispatcher())
    }

    @Test
    fun `get news list api call returns success`() = runTest {
        coEvery { newsFeedApi.getNewsListDto() } returns newsListDto
        newsFeedRepository.getNewsListDto()

        coVerify { newsFeedApi.getNewsListDto() }
    }
}