package com.demo.news.presentation.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.news.domain.usecase.newslist.GetNewsListUseCase
import com.demo.news.utils.Constants.CONNECTION_ERROR_MESSAGE
import com.demo.news.utils.Constants.DEFAULT_ERROR_MESSAGE
import com.demo.news.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

/**
 * News list view model
 *
 * @property getNewsListUseCase
 *
 * view model associated with the news list screen
 */
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsListUiState())
    val state: StateFlow<NewsListUiState> = _state

    init {
        getNewsList()
    }

    /**
     * Get news list
     *
     * returns the news list or an error message which will be displayed on the screen
     */
    fun getNewsList() {
        getNewsListUseCase().onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = NewsListUiState(
                        newsList = result.data
                    )
                }

                is Result.Error -> {
                    _state.value = NewsListUiState(
                        errorMessage = when (result.exception) {
                            is IOException -> {
                                CONNECTION_ERROR_MESSAGE
                            }

                            else -> {
                                DEFAULT_ERROR_MESSAGE
                            }
                        }
                    )
                }

                is Result.Loading -> {
                    _state.value = NewsListUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}