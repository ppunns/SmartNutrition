package com.example.smartnutrition.data.remote.dto

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val profile_picture: String,
    val proteinTarget: Int
)