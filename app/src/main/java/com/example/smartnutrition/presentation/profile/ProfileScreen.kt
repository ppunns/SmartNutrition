package com.example.smartnutrition.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnutrition.presentation.common.PrimaryButton
import com.example.smartnutrition.presentation.profile.components.ProfileAvatar
import com.example.smartnutrition.presentation.profile.components.ProfileMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
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
                    photoUrl = "https://example.com/photo.jpg",
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
                icon = Icons.Outlined.Person,
                title = "Username",
                subtitle = "Kaspun",
                onClick = { /* Handle click */ }
            )
            ProfileMenuItem(
                icon = Icons.Outlined.Person,
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
                icon = Icons.Outlined.Person,
                title = "Protein",
                subtitle = "Pantau dan Capai Target Anda Setiap Hari",
                onClick = { /* Handle click */ }
            )
            ProfileMenuItem(
                icon = Icons.Outlined.Person,
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
        }
    }
}
@Composable
private fun SettingsItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    hasSwitch: Boolean = false,
    switchChecked: Boolean = false,
    onSwitchChange: ((Boolean) -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF2196F3),
            modifier = Modifier.size(24.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
        if (hasSwitch) {
            Switch(
                checked = switchChecked,
                onCheckedChange = onSwitchChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF2196F3),
                    checkedTrackColor = Color(0xFFBBDEFB)
                )
            )
        }
    }
}