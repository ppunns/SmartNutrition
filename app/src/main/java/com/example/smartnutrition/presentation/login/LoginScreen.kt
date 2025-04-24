package com.example.smartnutrition.presentation.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Typography
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.smartnutrition.presentation.common.BlueCircleElement
import com.example.smartnutrition.presentation.common.EmailInput
import com.example.smartnutrition.presentation.common.PasswordInput
import com.example.smartnutrition.presentation.common.PrimaryButton
import com.example.smartnutrition.ui.theme.Blue100
import com.example.smartnutrition.ui.theme.Blue200
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.MobileTypography

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.state.collectAsState()

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
            
            Text(
                text = "Login Disini",
                style = MobileTypography.headlineLarge,  // Tetap menggunakan headlineLarge
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Selamat Datang Kembali!",
                style = MobileTypography.bodySmall,  // Tetap menggunakan bodyLarge
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Email",
                style = MobileTypography.titleMedium,  // Ubah ke titleMedium
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            EmailInput(
                value = email,
                onValueChange = { email = it },
                isError = state.error != null,
                errorMessage = state.error ?: ""
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Password",
                style = MobileTypography.titleMedium,  // Ubah ke titleMedium
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordInput(
                value = password,
                onValueChange = { password = it },
                isError = state.error != null,
                errorMessage = state.error ?: ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Lupa password?",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
                    .clickable { /* Handle forgot password */ },
                style = MobileTypography.labelSmall,  // Ubah ke bodySmall
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                PrimaryButton(
                    onClick = { viewModel.login(email, password) },
                    text = "Login",
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Belum punya akun? ",
                    style = MobileTypography.labelLarge,  // Ubah ke bodySmall
                    color = Color.Gray
                )
                Text(
                    text = "Daftar di sini!",
                    style = MobileTypography.labelSmall,  // Ubah ke bodySmall
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        viewModel = hiltViewModel(),
        navController = rememberNavController()
    )
}
@Preview(showBackground = true)
@Composable
fun BlueCircleElementPreview() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        BlueCircleElement(
            modifier = Modifier.fillMaxSize()
        )
    }
}
