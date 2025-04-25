package com.example.smartnutrition.presentation.register


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smartnutrition.presentation.common.BlueCircleElement
import com.example.smartnutrition.presentation.common.EmailInput
import com.example.smartnutrition.presentation.common.PasswordInput
import com.example.smartnutrition.presentation.common.PrimaryButton
import com.example.smartnutrition.presentation.common.UsernameInput
import com.example.smartnutrition.presentation.login.LoginScreen
import com.example.smartnutrition.presentation.login.SimpleCard
import com.example.smartnutrition.presentation.navgraph.Route
import com.example.smartnutrition.presentation.register.component.TermsAndConditionsCheckbox
import com.example.smartnutrition.ui.theme.Blue
import com.example.smartnutrition.ui.theme.MobileTypography
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController
) {


    var isHeaderVisible by remember { mutableStateOf(false) }
    var isUsernameVisible by remember { mutableStateOf(false) }
    var isEmailVisible by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(false) }
    var isFooterVisible by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    // Mengatur timing animasi
    LaunchedEffect(Unit) {
        delay(100) // Delay awal
        isHeaderVisible = true
        delay(200) // Delay antar komponen
        isUsernameVisible = true
        delay(200) // Delay antar komponen
        isEmailVisible = true
        delay(200)
        isPasswordVisible = true
        delay(200)
        isButtonVisible = true
        delay(200)
        isFooterVisible = true
    }

    val state by viewModel.state.collectAsState()
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.RegisterScreen.route) { inclusive = true }
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
            ){
                Column {  // <- TAMBAHKAN Column di sini
                    Text(
                        text = "Daftar Disini",
                        style = MobileTypography.headlineLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Buat akun baru untuk memulai!",
                        style = MobileTypography.bodySmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))
            AnimatedVisibility(
                visible = isUsernameVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ){
                Column {
                    Text(
                        text = "Username",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    UsernameInput(
                        value = state.username,
                        onValueChange = viewModel::onUsernameChange,
                        isError = state.error != null,
                        errorMessage = state.error ?: ""
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Email Input
            AnimatedVisibility(
                visible = isUsernameVisible,  // <- MASALAH: menggunakan isUsernameVisible alih-alih isEmailVisible
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ){
                Column {
                    Text(
                        text = "Email",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    EmailInput(
                        value = state.email,
                        onValueChange = viewModel::onEmailChange,
                        isError = state.error != null,
                        errorMessage = state.error ?: ""
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
            ){
                Column {
                    Text(
                        text = "Password",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordInput(
                        value = state.password,
                        onValueChange = viewModel::onPasswordChange,
                        isError = state.error != null,
                        errorMessage = state.error ?: ""
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
            ){
                Column {
                    Text(
                        text = "Konfirmasi Password",
                        style = MobileTypography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PasswordInput(
                        value = state.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        isError = state.error != null,
                        errorMessage = state.error ?: ""
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TermsAndConditionsCheckbox(
                                isChecked = checked,
                                onCheckedChange = { checked = it },
                                onTermsClicked = { /* Open Terms and Conditions */ },
                                onPrivacyPolicyClicked = { /* Open Privacy Policy */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically(
                    initialOffsetY = { 50 },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            ){
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    PrimaryButton(
                        onClick = { viewModel.register() },
                        text = "Daftar",
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            // Footer seharusnya dibungkus dengan AnimatedVisibility
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
                        text = "Sudah punya akun? ",
                        style = MobileTypography.labelLarge,
                        color = Color.Gray
                    )
                    Text(
                        text = "Login di sini!",
                        modifier = Modifier.clickable {
                            navController.navigate(Route.LoginScreen.route) {
                                popUpTo(Route.RegisterScreen.route) { inclusive = true }
                            }
                        },
                        style = MobileTypography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    RegisterScreen(
        viewModel = hiltViewModel(),
        navController = rememberNavController()
    )
}