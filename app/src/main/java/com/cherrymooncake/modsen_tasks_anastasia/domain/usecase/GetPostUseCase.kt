package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.error.ExceptionDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.utils.TResult

class GetPostsUseCase (
    private val postRemoteRepository: IPostRepository
) {
    suspend operator fun invoke() : TResult<List<PostDomainModel>, ExceptionDomainModel>
            = postRemoteRepository.getPosts()
}