package com.cherrymooncake.modsen_tasks_anastasia.ui.details

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

data class PostCommentsState (
    val post: PostDomainModel,
    val isLoading: Boolean = true,
    val comments: List<CommentUiModel> = emptyList(),
    val error: Int? = null
)

sealed interface PostCommentsEvent {
}

sealed interface PostCommentsIntent {
    data object ToggleFavorite : PostCommentsIntent
}
