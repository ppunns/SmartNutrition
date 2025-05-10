package com.example.smartnutrition.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    fun resetSnackbarMessage() {
        _snackbarMessage.value = null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                
                // Tambahkan delay 2 detik
                delay(2000)
                
                authRepository.login(email, password)
                    .onSuccess { response ->
                        // Save the actual token from response
                        tokenManager.saveToken(response.token)
                        tokenManager.saveUserId(response.user.id.toString())
                        _state.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false
                            )
                        }
                        _snackbarMessage.value = "Login berhasil"
                    }
                    .onFailure { exception ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message ?: "Login failed"
                            )
                        }
                        _snackbarMessage.value = "Login gagal"
                    }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unexpected error occurred"
                    )
                }
                _snackbarMessage.value = "Login gagal"
            }
        }
    }
}
