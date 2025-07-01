package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

data class PostsState(
    val posts: List<PostUiModel> = emptyList(),
    val error: Int? = null,
    val isLoading: Boolean = false
)

sealed interface PostsIntent {

}

sealed interface PostsEvent {

}
