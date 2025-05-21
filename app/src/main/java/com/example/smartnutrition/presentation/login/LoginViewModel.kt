package com.example.smartnutrition.presentation.login

import android.util.Patterns
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
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val tokenManager: TokenManager,
    private val authRepository: AuthRepository,
) : ViewModel() {
    
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    fun resetSnackbarMessage() {
        _snackbarMessage.value = null
    }

    fun showMessage(message: String) {
        _snackbarMessage.value = message
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPassword(password: String): Boolean {
        // Minimal 8 karakter
        if (password.length < 8) {
            return false
        }

        // Harus mengandung minimal satu huruf besar
        var containsUppercase = false
        for (char in password) {
            if (char.isUpperCase()) {
                containsUppercase = true
                break
            }
        }

        return containsUppercase
    }
//    fun isValidPassword(password: String): Boolean {
//        return password.length >= 8 // minimal 8 karakter dan harus mengandung huruf besar
//    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                if (email.isBlank() || password.isBlank()) {
//                    _snackbarMessage.value = "Email atau password harus diisi dulu"
                    return@launch
                }

                if (!isValidPassword(password)) {
//                    _snackbarMessage.value = "Password minimal 8 karakter"
                    return@launch
                }
                
                if (!isValidEmail(email)) {
//                    _snackbarMessage.value = "Format email tidak valid"
                    return@launch
                }
                
                _state.update { it.copy(isLoading = true, error = null) }
                
                // Tambahkan delay 2 detik
                delay(2000)
                authRepository.login(email, password)
                    .onSuccess { response ->
                        // Save the actual token from response
                        tokenManager.saveToken(response.token)
                        tokenManager.saveUserData(
                            user = response.user
                        )
                        _state.update {
                            it.copy(
                                isSuccess = true,
                                isLoading = false
                            )
                        }
                        _snackbarMessage.value = response.status
                    }
                    .onFailure { exception ->
                        val errorMessage = when (exception) {
                            is UnknownHostException -> "Tidak ada koneksi internet"
                            is SocketTimeoutException -> "Koneksi timeout"
                            is HttpException -> {
                                when (exception.code()) {
                                    401 -> "Email atau password salah"
                                    503 -> "Server sedang maintenance"
                                    else -> "Terjadi kesalahan: ${exception.message}"
                                }
                            }
                            else -> "Terjadi kesalahan yang tidak diketahui"
                        }
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = errorMessage
                            )
                        }
                        _snackbarMessage.value = errorMessage
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
