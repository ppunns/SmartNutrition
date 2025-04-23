package com.example.smartnutrition.data.remote

import com.example.smartnutrition.data.model.LoginRequest
import com.example.smartnutrition.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest):LoginResponse
}