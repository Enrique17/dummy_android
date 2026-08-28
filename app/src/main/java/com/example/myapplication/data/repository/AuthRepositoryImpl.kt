package com.example.myapplication.data.repository

import com.example.myapplication.domain.model.LoginResult
import com.example.myapplication.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(username: String, password: String): LoginResult {
        // Simulating a network call
        delay(2000)
        return if (username == "admin" && password == "1234") {
            LoginResult.Success("Admin User")
        } else {
            LoginResult.Error("Invalid credentials")
        }
    }
}
