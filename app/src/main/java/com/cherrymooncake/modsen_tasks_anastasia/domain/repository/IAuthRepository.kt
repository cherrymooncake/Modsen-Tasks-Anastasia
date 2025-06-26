package com.cherrymooncake.modsen_tasks_anastasia.domain.repository

interface IAuthRepository {
    suspend fun login(login: String, pass: String): Result<Unit>
}