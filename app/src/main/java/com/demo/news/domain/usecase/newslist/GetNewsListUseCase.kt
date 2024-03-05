package com.demo.news.domain.usecase.newslist

import com.demo.news.domain.model.NewsList
import com.demo.news.domain.repository.NewsRepository
import com.demo.news.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Get news list use case
 *
 * @property newsRepository
 *
 */
class GetNewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    /**
     * This will fetch the news list from the api
     * returns loading state, response or exception
     *
     */
    operator fun invoke(): Flow<Result<NewsList>> = flow {
        try {
            emit(Result.Loading)
            val newsList = newsRepository.getNewsListDto().toNewsList()
            emit(Result.Success(data = newsList))
        } catch (exception: Exception) {
            emit(Result.Error(exception))
        }
    }
}