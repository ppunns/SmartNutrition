package com.example.smartnutrition.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = Blue,
    error = DarkRed,
    surface = LightBlack
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = Blue,
    error = DarkRed,
    surface = Blue50
)

@Composable
fun SmartNutritionTheme(
    darkTheme: Boolean = false, // Mengabaikan isSystemInDarkTheme()
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicLightColorScheme(context) // Selalu menggunakan tema terang
        }
        else -> LightColorScheme // Selalu menggunakan tema terang
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}