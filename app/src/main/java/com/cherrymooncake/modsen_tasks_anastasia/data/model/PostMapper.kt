package com.cherrymooncake.modsen_tasks_anastasia.data.model

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

fun PostApiModel.toDomainModel(): PostDomainModel {
    return PostDomainModel(
        userId = userId,
        id = this.id,
        title = this.title,
        body = this.body
    )
}