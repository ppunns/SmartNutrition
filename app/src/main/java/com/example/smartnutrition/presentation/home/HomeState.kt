package com.example.smartnutrition.presentation.home

import com.example.smartnutrition.data.remote.dto.NutritionResponse

data class HomeState(
    val isLoading: Boolean = false,
    val data: NutritionResponse? = null,
    val error: String? = null
)
