package com.example.smartnutrition.presentation.camera

import android.graphics.Bitmap
import com.example.smartnutrition.data.model.FruitClassificationResult

data class CameraState(
    val capturedImage: Bitmap? = null,
    val isLoading: Boolean = false,
    val classification: FruitClassificationResult? = null,
    val error: String? = null
)