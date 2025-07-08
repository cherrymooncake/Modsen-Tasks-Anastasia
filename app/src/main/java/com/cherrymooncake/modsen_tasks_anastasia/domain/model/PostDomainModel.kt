package com.cherrymooncake.modsen_tasks_anastasia.domain.model

import java.io.Serializable

data class PostDomainModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
): Serializable