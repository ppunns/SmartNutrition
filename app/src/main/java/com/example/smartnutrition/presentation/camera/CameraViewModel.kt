package com.example.smartnutrition.presentation.camera

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.domain.usecases.ai.ClassifyFruitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
                                error = null
                            )
                        }
                    },
                    onFailure = { error ->
                        _state.update { 
                            it.copy(
                                isLoading = false,
                                error = error.message
                            )
                        }
                    }
                )
            }
        }
    }
}
