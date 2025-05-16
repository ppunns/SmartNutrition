package com.example.smartnutrition.domain.model

data class Data(
    val date: String,
    val items: List<Item>,
    val totalKalori: Int,
    val totalKarbohidrat: Double,
    val totalLemak: Double,
    val totalProtein: Double,
//    Data tambahan dari API fruits
    val energy: Energy,
    val id: String,
    val kalori: Int,
    val karbohidrat: Double,
    val lemak: Double,
    val minerals: Minerals,
    val name: String,
    val nitrogen: Double,
    val protein: Double,
    val water: Double
)