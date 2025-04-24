package com.example.smartnutrition.data.remote.dto

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val status: String
)
data class Data(
    val token: String
)