package com.cherrymooncake.modsen_tasks_anastasia.di

import com.cherrymooncake.modsen_tasks_anastasia.data.repository.AuthRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.repository.PostsRemoteRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.source.AuthDataSource
import com.cherrymooncake.modsen_tasks_anastasia.data.api.IPostApi
import com.cherrymooncake.modsen_tasks_anastasia.data.repository.PostsLocalRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsLocalRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRemoteRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.AddFavoritePostUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetCommentsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.LoginUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.RemoveFavoritePostUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.details.PostCommentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AuthDataSource() }
    single { IPostApi.create() }
    single<IAuthRepository> { AuthRepositoryImpl(get()) }
    single<IPostsRemoteRepository> { PostsRemoteRepositoryImpl(get()) }
    single<IPostsLocalRepository> { PostsLocalRepositoryImpl(get()) }
}

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { GetPostsUseCase(get(), get()) }
    factory { GetCommentsUseCase(get()) }
    factory { AddFavoritePostUseCase(get()) }
    factory { RemoveFavoritePostUseCase(get()) }
}

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel {
        PostsViewModel(
            getPostsUseCase = get(),
            addFavoritePostUseCase = get(),
            removeFavoritePostUseCase = get()
        )
    }
    viewModel { (post: PostDomainModel) ->
        PostCommentsViewModel(
            post = post,
            getCommentsUseCase = get(),
            addFavoritePostUseCase = get(),
            removeFavoritePostUseCase = get()
        )
    }
}
