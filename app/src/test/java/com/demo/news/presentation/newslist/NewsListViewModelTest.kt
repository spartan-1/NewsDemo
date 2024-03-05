package com.demo.news.presentation.newslist

import com.demo.news.BaseTest
import com.demo.news.domain.model.NewsItem
import com.demo.news.domain.model.NewsList
import com.demo.news.domain.usecase.newslist.GetNewsListUseCase
import com.demo.news.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest : BaseTest() {

    private lateinit var newsListViewModel: NewsListViewModel
    private val newsListUseCase: GetNewsListUseCase = mockk()
    private val newsList: NewsList = mockk()

    @BeforeEach
    override fun setUp() {
        super.setUp()
        coEvery { newsListUseCase() } returns flow {
            emit(Result.Loading)
        }
        newsListViewModel = NewsListViewModel(newsListUseCase)
    }

    @Test
    fun `test response when get news list returns loading state`() = runTest {
        coEvery { newsListUseCase() } returns flow {
            emit(Result.Loading)
        }
        newsListViewModel.getNewsList()
        advanceUntilIdle()
        coVerify { newsListUseCase() }
        Assertions.assertTrue(newsListViewModel.state.value.isLoading)
    }

    @Test
    fun `test response when get news list returns success`() = runTest {
        coEvery { newsList.newsList } returns listOf(
            NewsItem(
                enclosureLink = "https://live-production.wcms.abc-cdn.net.au/48b8c0c2b02226569b74ca56e4dd59bc?impolicy=wcms_crop_resize&cropH=1687&cropW=2998&xPos=0&yPos=13&width=862&height=485\",\n" +
                        "\"type\":\"image/jpeg",
                title = "Title",
                author = "Author"
            )
        )
        coEvery { newsListUseCase() } returns flow {
            emit(Result.Success(newsList))
        }
        newsListViewModel.getNewsList()
        advanceUntilIdle()
        coVerify { newsListUseCase() }
        Assertions.assertTrue(newsListViewModel.state.value.newsList.newsList.size == 1)
    }

    @Test
    fun `test error message when get news list returns exception`() = runTest {
        coEvery { newsListUseCase.invoke() } returns flow {
            emit(Result.Error(Exception()))
        }
        newsListViewModel.getNewsList()
        advanceUntilIdle()
        coVerify { newsListUseCase() }
        Assertions.assertTrue(newsListViewModel.state.value.errorMessage == DEFAULT_ERROR_MESSAGE)
    }

    @Test
    fun `test error message when get news list returns io exception`() = runTest {
        coEvery { newsListUseCase.invoke() } returns flow {
            emit(Result.Error(IOException()))
        }
        newsListViewModel.getNewsList()
        advanceUntilIdle()
        coVerify { newsListUseCase() }
        Assertions.assertTrue(newsListViewModel.state.value.errorMessage == CONNECTION_ERROR_MESSAGE)
    }
}