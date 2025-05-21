package com.example.smartnutrition.presentation.login




data class LoginState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val email: String = "",
    val password: String = ""
)