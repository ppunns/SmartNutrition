package com.example.smartnutrition.data.remote.dto

import com.example.smartnutrition.domain.model.Data

data class DailyNutritionResponse(
    val `data`: Data,
    val success: Boolean
)