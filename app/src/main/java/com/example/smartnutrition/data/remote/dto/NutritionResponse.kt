package com.example.smartnutrition.data.remote.dto

import com.example.smartnutrition.domain.model.Data
import com.example.smartnutrition.domain.model.Item


data class NutritionResponse(
    val `data`: Data,
    val success: Boolean
)

