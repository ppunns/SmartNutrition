package com.example.smartnutrition.presentation.profile

data class ProfileState(
    val id: Int = 0,
    val proteinTarget: Int = 0,
    val username: String = "",
    val email: String = "",
    val profilePicture: String = "",
    val isDarkMode: Boolean = false,
    val selectedLanguage: String = "Indonesia",
    val isLoading: Boolean = false,
    val error: String? = null
)