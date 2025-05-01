package com.example.smartnutrition.presentation.splash


import androidx.lifecycle.ViewModel
import com.example.smartnutrition.data.manager.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {

    fun hasToken(): Boolean {
        return !tokenManager.getToken().isNullOrEmpty()
    }
}