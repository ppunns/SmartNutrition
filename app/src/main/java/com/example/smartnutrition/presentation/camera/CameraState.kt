package com.example.smartnutrition.presentation.camera

import android.graphics.Bitmap
import com.example.smartnutrition.data.model.FruitClassificationResult
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.model.Data

data class CameraState(
    val capturedImage: Bitmap? = null,
    val isLoading: Boolean = false,
    val classification: FruitClassificationResult? = null,
    val fruitData: Data? = null,
    val error: String? = null
)