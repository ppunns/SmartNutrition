package com.example.smartnutrition.presentation.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnutrition.data.remote.dto.User
import com.example.smartnutrition.presentation.common.NutritionInputBottomSheet
import com.example.smartnutrition.presentation.navgraph.Route
import com.example.smartnutrition.presentation.profile.components.ProfileAvatar
import com.example.smartnutrition.presentation.profile.components.ProfileMenuItem
import com.example.smartnutrition.ui.icons.AppIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()


//    Test Component Baru
    var showBottomSheet by remember { mutableStateOf(false) }
    var karbohidratValue by remember { mutableStateOf("854") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Profile",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                modifier = Modifier.border(
                    width = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 25.dp)
        ) {
            // Profile Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                ProfileAvatar(
                    photoUrl = "",
                    username = "Kaspun",
                    onEditClick = {}
                )
            }

            Text(
                text = "Profile",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Profile Info Section
            ProfileMenuItem(
                icon = AppIcons.profile,
                title = "Username",
                subtitle = "Kaspun",
                onClick = { /* Handle click */ }
            )
            ProfileMenuItem(
                icon = AppIcons.email,
                title = "Email",
                subtitle = "kaspun123@gmail.com",
                onClick = { /* Handle click */ }
            )
            Text(
                text = "Pangaturan Umum",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            ProfileMenuItem(
                icon = AppIcons.protein,
                title = "Protein",
                subtitle = "Pantau dan Capai Target Anda Setiap Hari",
                onClick = {
//                    showInputKalori = true
                    showBottomSheet = true
                }
            )
            ProfileMenuItem(
                icon = AppIcons.bulan,
                title = "Mode Gelap",
                subtitle = "Aktifkan Tema Gelap",
                hasSwitch = true,
                onSwitchChange = { viewModel.toggleDarkMode() }
            )
            ProfileMenuItem(
                icon = AppIcons.bumi,
                title = "Bahasa",
                subtitle = "Pilih bahasa",
                onClick = { /* Handle click */ }
            )

            Spacer(modifier = Modifier.weight(1f))
            // Logout Button
            Button(
                onClick = {
                    viewModel.logout()
                    onNavigateToLogin()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5252)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Keluar", color = Color.White)
            }

//            Logika Show ButtonSHeet
            if (showBottomSheet) {
                NutritionInputBottomSheet(
                    title = "Protein",
                    subtitle = "Pantau dan Capai Target Anda Setiap Hari",
                    label = "Karbohidrat",
                    initialValue = karbohidratValue,
                    onSave = { newValue ->
                        karbohidratValue = newValue
                    },
                    onDismiss = { showBottomSheet = false }
                )
            }
        }
    }
}


