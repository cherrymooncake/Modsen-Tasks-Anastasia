package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.error.parseToString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostsState())
    val uiState: StateFlow<PostsState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getPostsUseCase.invoke()) {
                is TResult.Success -> {
                    _uiState.update { it.copy(
                        posts = result.data.map { domainPost -> domainPost.toUiModel() },
                        isLoading = false
                    )}
                }

                is TResult.Error -> {
                    _uiState.update { it.copy(
                        error = result.exception.parseToString(),
                        isLoading = false
                    )}
                }
            }
        }
    }
}