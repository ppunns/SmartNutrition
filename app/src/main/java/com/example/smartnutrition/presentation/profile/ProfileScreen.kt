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

    var showBottomSheet by remember { mutableStateOf(false) }
    var karbohidratValue by remember { mutableStateOf(state.proteinTarget.toString()) }
    val proteinTarget by viewModel.proteinTarget.collectAsState()



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
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
                    color = Color(0xFF006FFD).copy(alpha = 0.1f)
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
                    photoUrl = state.profilePicture,
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
                subtitle = state.username,
                onClick = { /* Handle click */ }
            )
            ProfileMenuItem(
                icon = AppIcons.email,
                title = "Email",
                subtitle = state.email,
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
                    showBottomSheet = true
                }
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
                    label = "Target Protein",
                    initialValue = proteinTarget.toString(),
                    onSave = { newValue ->
                        karbohidratValue = newValue
                        viewModel.updateProteinTarget(newValue.toIntOrNull() ?: 0)
                        showBottomSheet = false
                    },
                    onDismiss = { showBottomSheet = false }
                )
            }
        }
    }
}


