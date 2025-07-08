package com.cherrymooncake.modsen_tasks_anastasia.data.model

import com.google.gson.annotations.SerializedName

data class CommentApiModel(
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)