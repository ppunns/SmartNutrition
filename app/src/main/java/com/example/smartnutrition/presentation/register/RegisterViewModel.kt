package com.example.smartnutrition.presentation.register


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
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    fun onUsernameChange(username: String) {
        _state.update { it.copy(username = username) }
    }

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }
    fun showMessage(message: String) {
        _snackbarMessage.value = message
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
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

    fun register() {
        val currentState = _state.value
        if (!isValidEmail(currentState.email)) {
            return
        }
        if (!isValidPassword(currentState.password)) {
            return
        }
        if (currentState.username.isBlank()) {
            _state.update { it.copy(error = "Username tidak boleh kosong") }
            _snackbarMessage.value = "Username tidak boleh kosong"
            return
        }

        if (currentState.email.isBlank()) {
            _state.update { it.copy(error = "Email tidak boleh kosong") }
            _snackbarMessage.value = "Email tidak boleh kosong"
            return
        }

        if (currentState.password.isBlank()) {
            _state.update { it.copy(error = "Password tidak boleh kosong") }
            _snackbarMessage.value = "Password tidak boleh kosong"
            return
        }

        if (currentState.confirmPassword.isBlank()) {
            _state.update { it.copy(error = "Konfirmasi password tidak boleh kosong") }
            _snackbarMessage.value = "Konfirmasi password tidak boleh kosong"
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            _state.update { it.copy(error = "Password tidak cocok") }
            _snackbarMessage.value = "Password tidak cocok"
            return
        }

        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true, error = null) }
                
                // Add 2 seconds delay for loading screen
                delay(2000)
                
                repository.register(
                    username = currentState.username,
                    email = currentState.email,
                    password = currentState.password
                ).onSuccess { response ->
                    // Save token after successful registration
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
                }.onFailure { exception ->
                    val errorMessage = when (exception) {
                        is UnknownHostException -> "Tidak ada koneksi internet"
                        is SocketTimeoutException -> "Koneksi timeout"
                        is HttpException -> {
                            when (exception.code()) {
                                400 -> "Email sudah terdaftar"
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
                        error = e.message ?: "Terjadi kesalahan"
                    )
                }
                _snackbarMessage.value = "Registrasi gagal"
            }
        }
    }
}