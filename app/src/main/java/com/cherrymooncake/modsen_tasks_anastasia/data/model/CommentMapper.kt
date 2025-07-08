package com.cherrymooncake.modsen_tasks_anastasia.data.model

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.CommentDomainModel

fun CommentApiModel.toDomainModel(): CommentDomainModel {
    return CommentDomainModel(
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body
    )
}