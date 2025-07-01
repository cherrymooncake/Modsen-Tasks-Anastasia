package com.cherrymooncake.modsen_tasks_anastasia.ui.posts

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

data class PostUiModel (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

fun PostDomainModel.toUiModel(): PostUiModel {
    return PostUiModel(
        userId = this.userId,
        id = this.id,
        title = this.title,
        body = this.body
    )
}
