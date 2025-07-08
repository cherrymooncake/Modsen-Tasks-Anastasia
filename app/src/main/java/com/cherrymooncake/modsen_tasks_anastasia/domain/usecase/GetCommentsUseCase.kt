package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRepository

class GetCommentsUseCase(private val postsRepository: IPostsRepository) {
    suspend operator fun invoke(postId: Int) = postsRepository.getComments(postId)
}