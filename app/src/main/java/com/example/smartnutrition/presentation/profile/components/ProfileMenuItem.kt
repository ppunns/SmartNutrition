package com.example.smartnutrition.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.theme.MobileTypography
import com.example.smartnutrition.ui.theme.SmartNutritionTheme

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .width(50.dp)
                .height(39.dp)
                .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF2196F3),
                modifier = Modifier.size(20.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 5.dp)
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MobileTypography.titleMedium,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MobileTypography.labelLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemPreview() {
    SmartNutritionTheme {
        ProfileMenuItem(
            icon = Icons.Outlined.Person,
            title = "Username",
            subtitle = "Kaspun",
            onClick = { /* Handle click */ }
        )
    }
}