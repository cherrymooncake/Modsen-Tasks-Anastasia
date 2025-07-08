package com.cherrymooncake.modsen_tasks_anastasia.domain.repository

import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult
import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.CommentDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel

interface IPostsRepository {
    suspend fun getPosts(): TResult<List<PostDomainModel>, ExceptionDomainModel>
    suspend fun getComments(postId: Int): TResult<List<CommentDomainModel>, ExceptionDomainModel>
}
