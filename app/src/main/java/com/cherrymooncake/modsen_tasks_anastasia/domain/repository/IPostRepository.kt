package com.cherrymooncake.modsen_tasks_anastasia.domain.repository

import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

interface IPostRepository {
    suspend fun getPosts(): TResult<List<PostDomainModel>, ExceptionDomainModel>
}
