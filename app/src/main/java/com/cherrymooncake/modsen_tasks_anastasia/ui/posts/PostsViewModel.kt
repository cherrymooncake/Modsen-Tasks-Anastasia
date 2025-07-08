package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.error.parseToString
import com.cherrymooncake.modsen_tasks_anastasia.ui.utils.SingleFlowEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PostsState())
    val state: StateFlow<PostsState> = _state.asStateFlow()

    private val _event = SingleFlowEvent<PostsEvent>(viewModelScope)
    val event = _event.flow

    init {
        sendIntent(PostsIntent.LoadPosts)
    }

    fun sendIntent(intent: PostsIntent) {
        when (intent) {
            is PostsIntent.LoadPosts -> loadPosts()
            is PostsIntent.OnSearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
                filterPosts()
            }
            is PostsIntent.OnPostClick -> {
                viewModelScope.launch {
                    _event.emit(PostsEvent.NavigateToPostDetails(intent.post))
                }
            }
        }
    }

    private fun filterPosts() {
        val query = _state.value.searchQuery
        val postsToFilter = _state.value.allPosts

        val filtered = if (query.isBlank()) {
            postsToFilter
        } else {
            postsToFilter.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.body.contains(query, ignoreCase = true)
            }
        }
        _state.update { it.copy(filteredPosts = filtered) }
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            when (val result = getPostsUseCase()) {
                is TResult.Success -> {
                    val uiPosts = result.data.map { domainPost -> domainPost.toUiModel() }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allPosts = uiPosts,
                            filteredPosts = uiPosts
                        )
                    }
                    filterPosts()
                }
                is TResult.Error -> {
                    _state.update {
                        it.copy(
                            error = result.exception.parseToString(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}