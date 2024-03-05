package com.demo.news

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong"
        const val CONNECTION_ERROR_MESSAGE = "Couldn't reach server. Check your internet connection."
    }
}