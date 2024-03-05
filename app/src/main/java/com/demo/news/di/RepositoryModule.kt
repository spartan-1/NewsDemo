package com.demo.news.di

import com.demo.news.data.remote.NewsFeedApi
import com.demo.news.data.repository.NewsFeedRepositoryImpl
import com.demo.news.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides news feed repository
     *
     * @param newsFeedApi
     * @param dispatcher
     * @return
     */
    @Provides
    @Singleton
    fun providesNewsFeedRepository(
        newsFeedApi: NewsFeedApi,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): NewsRepository {
        return NewsFeedRepositoryImpl(newsFeedApi, dispatcher)
    }
}