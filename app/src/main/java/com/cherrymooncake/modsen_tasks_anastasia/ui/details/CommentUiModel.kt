package com.cherrymooncake.modsen_tasks_anastasia.ui.details

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.CommentDomainModel

data class CommentUiModel(
    val id: Int,
    val name: String,
    val body: String
)

fun CommentDomainModel.toCommentUiModel(): CommentUiModel {
    return CommentUiModel(
        id = this.id,
        name = this.name.replaceFirstChar { it.uppercase() },
        body = this.body
    )
}
