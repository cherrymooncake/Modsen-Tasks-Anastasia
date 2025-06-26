package com.cherrymooncake.modsen_tasks_anastasia.data.source

import kotlinx.coroutines.delay

class AuthDataSource {
    private val validCredentials = mapOf(
        "user" to "pass",
        "admin" to "adminpass"
    )

    class InvalidCredentialsException : Exception("Invalid login or password")

    suspend fun login(login: String, pass: String) {
        delay(1000)

        if (validCredentials[login] != pass) {
            throw InvalidCredentialsException()
        }
    }
}