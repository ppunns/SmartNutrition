package com.example.smartnutrition.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.theme.MobileTypography

@Composable
fun ProfileMenuItem(
    icon: Int,
    title: String,
    subtitle: String? = null,
    hasSwitch: Boolean = false,
    switchChecked: Boolean = false,
    onSwitchChange: ((Boolean) -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp),
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
        ) {
            Icon(
                painter = painterResource(id = icon),
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
        
        if (hasSwitch) {
            Switch(
                checked = switchChecked,
                onCheckedChange = onSwitchChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF2196F3),
                    checkedTrackColor = Color(0xFFBBDEFB),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedBorderColor = Color.Transparent,
                    checkedBorderColor = Color.Transparent
                ),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}