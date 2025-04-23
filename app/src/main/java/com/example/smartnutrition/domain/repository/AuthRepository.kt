package com.example.smartnutrition.domain.repository

import com.example.smartnutrition.data.remote.dto.LoginResponse

interface AuthRepository {
    suspend fun login(email: String,password: String):Result<LoginResponse>
}