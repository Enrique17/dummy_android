package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.LoginResult

interface AuthRepository {
    suspend fun login(username: String, password: String): LoginResult
}
