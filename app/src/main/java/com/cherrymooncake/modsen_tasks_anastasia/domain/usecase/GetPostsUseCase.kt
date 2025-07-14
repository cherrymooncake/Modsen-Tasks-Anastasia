package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsLocalRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRemoteRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetPostsUseCase(
    private val remoteRepository: IPostsRemoteRepository,
    private val localRepository: IPostsLocalRepository
) {
    operator fun invoke(): Flow<TResult<List<PostDomainModel>, ExceptionDomainModel>> = flow {
        val networkResult = withContext(Dispatchers.IO) {
            remoteRepository.getPosts()
        }

        when (networkResult) {
            is TResult.Success -> {
                val networkPosts = networkResult.data
                localRepository.getFavoritePostIds().collect { favoriteIds ->

                    val combinedPosts = networkPosts.map { post ->
                        post.copy(isFavorite = post.id in favoriteIds)
                    }
                    val sortedPosts = combinedPosts.sortedByDescending { it.isFavorite }
                    emit(TResult.Success(sortedPosts))
                }
            }
            is TResult.Error -> {
                emit(TResult.Error(networkResult.exception))
            }
        }
    }
}