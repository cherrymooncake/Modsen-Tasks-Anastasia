package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRemoteRepository

class GetCommentsUseCase(private val postsRepository: IPostsRemoteRepository) {
    suspend operator fun invoke(postId: Int) = postsRepository.getComments(postId)
}