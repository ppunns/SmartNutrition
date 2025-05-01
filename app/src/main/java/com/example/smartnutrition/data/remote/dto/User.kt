package com.example.smartnutrition.data.remote.dto

data class User(
    val email: String,
    val name: String,
    val profile_picture: String,
    val proteinTarget: Int
)