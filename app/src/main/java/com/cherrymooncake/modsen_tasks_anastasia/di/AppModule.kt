package com.cherrymooncake.modsen_tasks_anastasia.di

import com.cherrymooncake.modsen_tasks_anastasia.data.repository.AuthRepositoryImpl
import com.cherrymooncake.modsen_tasks_anastasia.data.source.AuthDataSource
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository
import com.cherrymooncake.modsen_tasks_anastasia.domain.usecase.LoginUseCase
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AuthDataSource() }
    single<IAuthRepository> { AuthRepositoryImpl(get()) }
}

val domainModule = module {
    factory { LoginUseCase(get()) }
}

val appModule = module {
    viewModel { LoginViewModel(get()) }
}
