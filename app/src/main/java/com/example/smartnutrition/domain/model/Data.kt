package com.example.smartnutrition.domain.model

data class Data(
    val date: String,
    val items: List<Item>,
    val totalKalori: Int,
    val totalKarbohidrat: Double,
    val totalLemak: Double,
    val totalProtein: Double,
    val fruitLabel: String,
    val imageUrl: String
)