package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.AddFavoritePostUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.RemoveFavoritePostUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.ui.error.parseToString
import com.cherrymooncake.modsen_tasks_anastasia.ui.utils.SingleFlowEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val addFavoritePostUseCase: AddFavoritePostUseCase,
    private val removeFavoritePostUseCase: RemoveFavoritePostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PostsState())
    val state = _state.asStateFlow()

    private val _event = SingleFlowEvent<PostsEvent>(viewModelScope)
    val eventFlow = _event.flow

    private var postsJob: Job? = null

    init {
        sendIntent(PostsIntent.LoadPosts)
    }

    fun sendIntent(intent: PostsIntent) {
        when (intent) {
            is PostsIntent.LoadPosts -> observePosts()
            is PostsIntent.OnPostClick -> {
                viewModelScope.launch { _event.emit(PostsEvent.NavigateToPostDetails(intent.post)) }
            }

            is PostsIntent.OnSearchQueryChanged -> {
                _state.update { it.copy(searchQuery = intent.query) }
                filterPosts()
            }

            is PostsIntent.ToggleFavorite -> toggleFavorite(intent.post)
        }
    }

    private fun toggleFavorite(post: PostDomainModel) {
        viewModelScope.launch {
            if (post.isFavorite) {
                removeFavoritePostUseCase(post)
            } else {
                addFavoritePostUseCase(post)
            }
        }
    }

    private fun observePosts() {
        postsJob?.cancel()

        _state.update { it.copy(isLoading = true) }

        postsJob = getPostsUseCase().onEach { result ->
            when (result) {
                is TResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            allPosts = result.data.map { it.toUiModel() },
                            error = null
                        )
                    }
                    filterPosts()
                }

                is TResult.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.exception.parseToString())
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun filterPosts() {
        val currentState = _state.value
        val filtered = if (currentState.searchQuery.isBlank()) {
            currentState.allPosts
        } else {
            currentState.allPosts.filter { post ->
                post.title.contains(currentState.searchQuery, ignoreCase = true) ||
                        post.body.contains(currentState.searchQuery, ignoreCase = true)
            }
        }
        _state.update { it.copy(filteredPosts = filtered) }
    }
}
