package com.example.smartnutrition.presentation.register

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)