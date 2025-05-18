package com.example.smartnutrition.data.remote

import com.example.smartnutrition.data.model.LoginRequest
import com.example.smartnutrition.data.model.RegisterRequest
import com.example.smartnutrition.data.remote.dto.LoginResponse
import com.example.smartnutrition.data.remote.dto.RegisterResponse
import com.example.smartnutrition.data.remote.dto.User
import retrofit2.http.*

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest):LoginResponse
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
    @GET("verify-token")
    suspend fun verifyToken(@Header("Authorization") token: String): User
}