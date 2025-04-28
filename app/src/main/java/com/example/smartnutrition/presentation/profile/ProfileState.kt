package com.example.smartnutrition.presentation.profile

data class ProfileState(
    val username: String = "",
    val email: String = "",
    val isDarkMode: Boolean = false,
    val selectedLanguage: String = "Indonesia",
    val isLoading: Boolean = false,
    val error: String? = null
)