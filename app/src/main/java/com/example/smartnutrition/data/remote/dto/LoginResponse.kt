package com.example.smartnutrition.data.remote.dto

data class LoginResponse(
    val status: String,
    val token: String,
    val user: User
)