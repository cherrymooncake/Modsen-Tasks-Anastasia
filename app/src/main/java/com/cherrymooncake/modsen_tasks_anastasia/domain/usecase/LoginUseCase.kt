package com.cherrymooncake.modsen_tasks_anastasia.domain.usecase

import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository

class LoginUseCase(private val authRepository: IAuthRepository) {
    suspend operator fun invoke(login: String, pass: String): Result<Unit> {
        return authRepository.login(login, pass)
    }
}