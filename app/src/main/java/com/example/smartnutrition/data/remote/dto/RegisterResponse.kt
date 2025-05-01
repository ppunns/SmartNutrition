package com.example.smartnutrition.data.remote.dto

data class RegisterResponse(
    val status: String,
    val token: String,
    val user: User
)