package com.cherrymooncake.modsen_tasks_anastasia.data.repository

import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.data.error.toExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.data.model.toDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.data.api.IPostApi
import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.CommentDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepositoryImpl(
    private val postsApi: IPostApi
) : IPostsRepository {
    override suspend fun getPosts(): TResult<List<PostDomainModel>, ExceptionDomainModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val postsFromApi = postsApi.getPosts()
                val mapPostsFromApi = postsFromApi.map{ it.toDomainModel() }
                TResult.Success<List<PostDomainModel>, ExceptionDomainModel>(mapPostsFromApi)
            }.getOrElse {
                TResult.Error(it.toExceptionDomainModel())
            }
        }
    override suspend fun getComments(postId: Int): TResult<List<CommentDomainModel>, ExceptionDomainModel> =
        withContext(Dispatchers.IO) {
            runCatching {
                val comments = postsApi.getComments(postId)
                val mapComments = comments.map { it.toDomainModel() }
                TResult.Success<List<CommentDomainModel>, ExceptionDomainModel>(mapComments)
            }.getOrElse {
                TResult.Error(it.toExceptionDomainModel())
            }
        }
}
