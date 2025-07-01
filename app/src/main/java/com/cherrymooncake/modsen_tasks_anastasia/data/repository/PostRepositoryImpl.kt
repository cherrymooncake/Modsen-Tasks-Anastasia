package com.cherrymooncake.modsen_tasks_anastasia.data.repository

import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.data.error.toExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.data.model.toDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.data.api.IPostApi
import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val postsApi: IPostApi
) : IPostRepository {
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
}
