package com.example.smartnutrition.presentation.home

import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.usecases.Nutrition.NutritionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val nutritionUseCase: NutritionUseCase
) : ViewModel() {
    private val _historyState = mutableStateOf(HomeState())
    val historyState: State<HomeState> = _historyState

    // Pindahkan deklarasi proteinTarget ke bagian atas setelah state
    private val _proteinTarget = MutableStateFlow(0)
    val proteinTarget = _proteinTarget.asStateFlow()

    init {
        fetchDailyNutrition()
        // Tambahkan inisialisasi protein target
        viewModelScope.launch {
            tokenManager.getProteinTarget.collect { target ->
                _proteinTarget.value = target
            }
        }
    }

    private suspend fun <T> safeApiCall(
        apiCall: suspend () -> Flow<Result<T>>,
        onSuccess: (T) -> Unit
    ) {
        try {
            _historyState.value = historyState.value.copy(isLoading = true)
            apiCall().collect { result ->
                result.onSuccess { response ->
                    onSuccess(response)
                    _historyState.value = historyState.value.copy(
                        isLoading = false,
                        error = null
                    )
                }.onFailure { exception ->
                    _historyState.value = historyState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Terjadi kesalahan"
                    )
                }
            }
        } catch (e: Exception) {
            _historyState.value = historyState.value.copy(
                isLoading = false,
                error = e.message ?: "Terjadi kesalahan"
            )
        }
    }

    private fun fetchDailyNutrition() {
        viewModelScope.launch {
            val userId = tokenManager.getUserData.first().id.toString()
            safeApiCall(
                apiCall = { nutritionUseCase.getDailyNutritionUseCase(userId) },
                onSuccess = { response ->
                    _historyState.value = historyState.value.copy(data = response)
                }
            )
        }
    }

    private fun fetchMonthlyNutrition() {
        viewModelScope.launch {
            val userId = tokenManager.getUserData.first().id.toString()
            safeApiCall(
                apiCall = { nutritionUseCase.getMonthlyNutritionUseCase(userId) },
                onSuccess = { response ->
                    _historyState.value = historyState.value.copy(data = response)
                }
            )
        }
    }

    fun updateHistoryType(isMonthly: Boolean) {
        if (isMonthly) {
            fetchMonthlyNutrition()
        } else {
            fetchDailyNutrition()
        }
    }
}
