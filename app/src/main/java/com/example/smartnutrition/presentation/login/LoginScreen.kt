package com.example.smartnutrition.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartnutrition.presentation.navgraph.Route
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Typography
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.smartnutrition.presentation.common.BlueCircleElement
import com.example.smartnutrition.presentation.common.EmailInput
import com.example.smartnutrition.presentation.common.PasswordInput
import com.example.smartnutrition.presentation.common.PrimaryButton
import com.example.smartnutrition.presentation.common.SharedViewModel
import com.example.smartnutrition.ui.theme.Blue100
import com.example.smartnutrition.ui.theme.Blue200
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.MobileTypography
import kotlinx.coroutines.delay


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Effect untuk menampilkan snackbar message
    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
            viewModel.resetSnackbarMessage() // Reset setelah menampilkan snackbar
        }
    }

    // State untuk animasi
    var isHeaderVisible by remember { mutableStateOf(false) }
    var isEmailVisible by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(false) }
    var isFooterVisible by remember { mutableStateOf(false) }

    // Mengatur timing animasi
    LaunchedEffect(Unit) {
        delay(100) // Delay awal
        isHeaderVisible = true
        delay(200) // Delay antar komponen
        isEmailVisible = true
        delay(200)
        isPasswordVisible = true
        delay(200)
        isButtonVisible = true
        delay(200)
        isFooterVisible = true
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.LoginScreen.route) { inclusive = true }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        // Background element
        BlueCircleElement(
            modifier = Modifier
                .size(700.dp)
                .offset(x = (140).dp, y = (-300).dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(139.dp))
            
            AnimatedVisibility(
                visible = isHeaderVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ) {
                Column {
                    Text(
                        text = "Login Disini",
                        style = MobileTypography.headlineLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Selamat Datang Kembali!",
                        style = MobileTypography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            AnimatedVisibility(
                visible = isEmailVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ) {
                Column {
                    Text(
                        text = "Email",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    EmailInput(
                        value = email,
                        onValueChange = { email = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = isPasswordVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ) {
                Column {
                    Text(
                        text = "Password",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordInput(
                        value = password,
                        onValueChange = { password = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = "Lupa password?",
//                        modifier = Modifier
//                            .align(Alignment.Start)
//                            .padding(top = 8.dp)
//                            .clickable { /* Handle forgot password */ },
//                        style = MobileTypography.labelSmall,
//                        color = MaterialTheme.colorScheme.primary
//                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ) {
                PrimaryButton(
                    onClick = { 
                        if (email.isBlank() || password.isBlank()) {
                            viewModel.showMessage("Silakan isi email dan password terlebih dahulu.")
                        } else {
                            viewModel.login(email, password)
                        }
                    },
                    text = "Login"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = isFooterVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Belum punya akun? ",
                        style = MobileTypography.labelLarge,
                        color = Color.Gray
                    )
                    Text(
                        text = "Daftar di sini!",
                        modifier = Modifier.clickable {
                            navController.navigate(Route.RegisterScreen.route)
                        },
                        style = MobileTypography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Loading overlay
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Sedang masuk...",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

            }
        }

        // Snackbar host
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            snackbar = { snackbarData ->
                Snackbar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    containerColor = if (snackbarMessage?.contains("salah") == true)
                        Color.Red 
                    else MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ) {
                    Text(text = snackbarData.visuals.message)
                }
            }
        )
    }
}



