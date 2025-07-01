package com.cherrymooncake.modsen_tasks_anastasia.di

import com.cherrymooncake.modsen_tasks_anastasia.data.repository.AuthRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.repository.PostRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.source.AuthDataSource
import com.cherrymooncake.modsen_tasks_anastasia.data.api.IPostApi
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IPostRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.GetPostsUseCase
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.LoginUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AuthDataSource() }
    single { IPostApi.create() }
    single<IAuthRepository> { AuthRepositoryImpl(get()) }
    single<IPostRepository> { PostRepositoryImpl(get()) }
}

val domainModule = module {
    factory { LoginUseCase(get()) }
    factory { GetPostsUseCase(get()) }
}

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { PostsViewModel(get()) }
}
