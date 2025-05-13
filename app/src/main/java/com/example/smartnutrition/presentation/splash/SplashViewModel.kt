package com.example.smartnutrition.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository
) : ViewModel() {

    private suspend fun verifyToken(): Boolean {
        return try {
            authRepository.verifyToken().isSuccess
        } catch (e: Exception) {
            false
        }
    }

    suspend fun isTokenValid(): Boolean {
        val token = tokenManager.getToken.first()
        return if (!token.isNullOrEmpty()) {
            verifyToken()
        } else {
            false
        }
    }
}