package com.cherrymooncake.modsen_tasks_anastasia.data.repository

import com.cherrymooncake.modsen_tasks_anastasia.data.model.FavoritePostDbModel
import com.cherrymooncake.modsen_tasks_anastasia.data.source.FavoritePostsDao
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostsLocalRepositoryImpl(
    private val favoritePostsDao: FavoritePostsDao
) : IPostsLocalRepository {

    override fun getFavoritePostIds(): Flow<Set<Int>> {
        return favoritePostsDao.getFavoritePostIds().map { it.toSet() }
    }

    override suspend fun addPostToFavorites(postId: Int) {
        favoritePostsDao.add(FavoritePostDbModel(id = postId))
    }

    override suspend fun removePostFromFavorites(postId: Int) {
        favoritePostsDao.remove(FavoritePostDbModel(id = postId))
    }
}