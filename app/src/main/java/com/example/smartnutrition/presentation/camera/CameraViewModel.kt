package com.example.smartnutrition.presentation.camera

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.model.FruitRequest
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.repository.NutritionRepository
import com.example.smartnutrition.domain.usecases.Nutrition.NutritionUseCase
import com.example.smartnutrition.domain.usecases.ai.ClassifyFruitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val classifyFruitUseCase: ClassifyFruitUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(CameraState())
    val state: StateFlow<CameraState> = _state.asStateFlow()

    fun classifyImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            classifyFruitUseCase(bitmap).collect { result ->
                result.fold(
                    onSuccess = { classification ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                capturedImage = bitmap,
                                classification = classification,
                                error = null,
                                shouldNavigateToDetail = true
                            )
                        }
                    },
                    onFailure = { exception ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message,
                                shouldNavigateToDetail = false
                            )
                        }
                    }
                )
            }
        }
    }

    fun resetNavigation() {
        _state.update { it.copy(shouldNavigateToDetail = false) }
    }
}
