package com.cherrymooncake.modsen_tasks_anastasia.di

import com.cherrymooncake.modsen_tasks_anastasia.data.repository.AuthRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.repository.PostsRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.source.AuthDataSource
import com.cherrymooncake.modsen_tasks_anastasia.data.api.IPostApi
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostsRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetCommentsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.LoginUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.details.PostCommentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AuthDataSource() }
    single { IPostApi.create() }
    single<IAuthRepository> { AuthRepositoryImpl(get()) }
    single<IPostsRepository> { PostsRepositoryImpl(get()) }
}

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { GetPostsUseCase(get()) }
    factory { GetCommentsUseCase(get()) }
}

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { PostsViewModel(get()) }
    viewModel { PostCommentsViewModel(get(), get()) }
    viewModel { (post: PostDomainModel) ->
        PostCommentsViewModel(
            post = post,
            getCommentsUseCase = get()
        )
    }
}
