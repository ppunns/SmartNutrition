package com.example.smartnutrition.domain.repository

import com.example.smartnutrition.data.remote.dto.LoginResponse
import com.example.smartnutrition.data.remote.dto.RegisterResponse
import com.example.smartnutrition.data.remote.dto.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse>
    suspend fun register(username: String, email: String, password: String): Result<RegisterResponse>
    suspend fun verifyToken(): Result<User>
}
