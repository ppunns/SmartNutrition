package com.example.smartnutrition.domain.model

data class Item(
    val fruitId: String,
    val fruitLabel: String,
    val fruitName: String,
    val id: String,
    val imageUrl: String?,
    val kalori: Int,
    val quantity: Int,
    val timestamp: String
)