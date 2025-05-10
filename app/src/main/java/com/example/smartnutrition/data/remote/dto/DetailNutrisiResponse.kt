package com.example.smartnutrition.data.remote.dto

data class DetailNutrisiResponse(
    val `data`: Data,
    val success: Boolean
)
data class Energy(
    val atwater: Int,
    val specific: Double
)

data class Data(
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
data class Minerals(
    val calcium: Int,
    val copper: Double,
    val iron: Double,
    val magnesium: Int,
    val manganese: Double,
    val phosphorus: Int,
    val potassium: Int,
    val sodium: Int,
    val zinc: Double
)