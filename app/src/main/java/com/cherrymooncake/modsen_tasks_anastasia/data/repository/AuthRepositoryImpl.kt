package com.cherrymooncake.modsen_tasks_anastasia.data.repository

import com.cherrymooncake.modsen_tasks_anastasia.data.source.AuthDataSource
import com.cherrymooncake.modsen_tasks_anastasia.domain.repository.IAuthRepository

class AuthRepositoryImpl (
    private val authDataSource: AuthDataSource
) : IAuthRepository {
    override suspend fun login(login: String, pass: String): Result<Unit> {
        return try {
            authDataSource.login(login, pass)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
