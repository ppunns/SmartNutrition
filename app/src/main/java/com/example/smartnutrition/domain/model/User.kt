package com.example.smartnutrition.domain.model

data class User(
    val email: String,
    val password: String,
    val username: String,
    val profilePhotoUrl: String? = null,
    val proteinTarget: Int = 0
)