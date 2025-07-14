package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsLocalRepository

class RemoveFavoritePostUseCase(private val localRepository: IPostsLocalRepository) {
    suspend operator fun invoke(post: PostDomainModel) {
        localRepository.removePostFromFavorites(post.id)
    }
}