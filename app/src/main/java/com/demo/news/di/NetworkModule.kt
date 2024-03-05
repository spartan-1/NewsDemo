package com.demo.news.di

import com.demo.news.data.remote.NewsFeedApi
import com.demo.news.utils.Constants.BASE_URL
import com.demo.news.utils.Constants.TIMEOUT_MILLI_SECS
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * Hilt module which provides network related dependencies
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provide http client
     *
     * @return
     */
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val logInterceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(logInterceptor)
            .readTimeout(TIMEOUT_MILLI_SECS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_MILLI_SECS, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provide moshi
     *
     * @return
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    /**
     * Provide converter factory
     *
     * @param moshi
     * @return
     */
    @Singleton
    @Provides
    fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    /**
     * Provide retrofit
     *
     * @param okHttpClient
     * @param moshiConverterFactory
     * @return
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    /**
     * Provide news feed api service
     *
     * @param retrofit
     * @return
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NewsFeedApi =
        retrofit.create(NewsFeedApi::class.java)
}