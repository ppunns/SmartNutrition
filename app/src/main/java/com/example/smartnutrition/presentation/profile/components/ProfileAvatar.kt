package com.example.smartnutrition.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun ProfileAvatar(
    photoUrl: String?,
    username: String,
    size: Int = 160,
    onEditClick: () -> Unit
) {
    Box(contentAlignment = Alignment.BottomEnd) {
        // Avatar container
        Box(
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), CircleShape)
                .clickable(onClick = onEditClick),
            contentAlignment = Alignment.Center
        ) {
            if (photoUrl != null) {
                // If we have a photo URL, load the image
                AsyncImage(
                    model = photoUrl,
                    contentDescription = "Profile Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Otherwise show the first letter of the username
                val initial = username.firstOrNull()?.toString() ?: ""
                Text(
                    text = initial,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Edit button
        IconButton(
            onClick = onEditClick,
            modifier = Modifier
                .size((size * 0.33).dp) // Edit button is ~1/3 the size of the avatar
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profile Photo",
                tint = Color.White
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileAvatarPreviewNoPhoto() {
    MaterialTheme {
        ProfileAvatar(
            photoUrl = null,
            username = "Kaspun",
            onEditClick = {}
        )
    }
}
// Preview with photo
@Preview(showBackground = true)
@Composable
fun ProfileAvatarPreviewWithPhoto() {
    MaterialTheme {
        ProfileAvatar(
            photoUrl = "https://example.com/photo.jpg",
            username = "Kaspun",
            onEditClick = {}
        )
    }
}
