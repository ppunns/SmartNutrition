package com.example.smartnutrition.data.model

data class AddNutritionRequest(
    val fruitLabel: String,
    val quantity: Int,
    val userId: String
)