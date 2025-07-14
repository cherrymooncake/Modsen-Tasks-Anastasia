package com.cherrymooncake.modsen_tasks_anastasia.domain.repository

import kotlinx.coroutines.flow.Flow

interface IPostsLocalRepository {
    fun getFavoritePostIds(): Flow<Set<Int>>
    suspend fun addPostToFavorites(postId: Int)
    suspend fun removePostFromFavorites(postId: Int)
}