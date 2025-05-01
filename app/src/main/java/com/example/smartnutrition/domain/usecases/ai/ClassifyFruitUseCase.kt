package com.example.smartnutrition.domain.usecases.ai

import android.graphics.Bitmap
import com.example.smartnutrition.data.model.FruitClassificationResult
import com.example.smartnutrition.domain.repository.FruitClassificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ClassifyFruitUseCase @Inject constructor(
    private val repository: FruitClassificationRepository
) {
    suspend operator fun invoke(bitmap: Bitmap): Flow<Result<FruitClassificationResult>> {
        return repository.classifyImage(bitmap)
    }
}