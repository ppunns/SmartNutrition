package com.example.smartnutrition.data.remote.dto

import com.example.smartnutrition.domain.model.Data
import com.example.smartnutrition.domain.model.Energy
import com.example.smartnutrition.domain.model.Item
import com.example.smartnutrition.domain.model.Minerals


data class NutritionResponse(
    val `data`: Data,
    val success: Boolean
)
