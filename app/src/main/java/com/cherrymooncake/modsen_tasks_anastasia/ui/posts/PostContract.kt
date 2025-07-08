package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

data class PostsState(
    val error: Int? = null,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val allPosts: List<PostUiModel> = emptyList(),
    val filteredPosts: List<PostUiModel> = emptyList()
)

sealed interface PostsIntent {
    data object LoadPosts : PostsIntent
    data class OnSearchQueryChanged(val query: String) : PostsIntent
    data class OnPostClick(val post: PostDomainModel) : PostsIntent
}

sealed interface PostsEvent {
    data class NavigateToPostDetails(val post: PostDomainModel) : PostsEvent
}
