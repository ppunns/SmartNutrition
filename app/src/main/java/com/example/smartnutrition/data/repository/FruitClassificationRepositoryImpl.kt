package com.example.smartnutrition.data.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.smartnutrition.data.model.FruitClassificationResult
import com.example.smartnutrition.domain.repository.FruitClassificationRepository
import com.example.smartnutrition.ml.Model
import com.example.smartnutrition.ml.Models
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

class FruitClassificationRepositoryImpl @Inject constructor(
    private val context: Context
) : FruitClassificationRepository {

    override suspend fun classifyImage(bitmap: Bitmap): Flow<Result<FruitClassificationResult>> = flow {
        try {
            val model = Models.newInstance(context)
            val imageSize = 32

            // Preprocess image
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 32, 32, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(imageSize * imageSize)
            scaledBitmap.getPixels(intValues, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)

            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val `val` = intValues[pixel++] // RGB
                    byteBuffer.putFloat((((`val` shr 16) and 0xFF) * (1f / 1))) // Seharusnya 1f/255f
                    byteBuffer.putFloat((((`val` shr 8) and 0xFF) * (1f / 1)))  // Seharusnya 1f/255f
                    byteBuffer.putFloat(((`val` and 0xFF) * (1f / 1)))          // Seharusnya 1f/255f
                }
            }
            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences = outputFeature0.floatArray
            var maxPos = 0
            var maxConfidence = 0f

            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }
            val classes = listOf(
                "Apple braeburn", "Wortel", "Timun", "Terong Panjang", "Pear"
            )
            val result = if (maxPos < classes.size) {
                FruitClassificationResult(classes[maxPos], maxConfidence)
            } else {
                throw IllegalStateException("Unknown class index: $maxPos")
            }

            model.close()
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}