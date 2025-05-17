package com.example.smartnutrition.presentation.common




import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.R
import com.example.smartnutrition.ui.theme.Blue500
import com.example.smartnutrition.ui.theme.MobileTypography

@Composable
fun SmartNutritionTopBar(
    onProfileClick: () -> Unit = {} // Tambahkan parameter untuk handle click
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 0.5.dp,
                color = Color(0xFF006FFD).copy(alpha = 0.1f)
            )
            .shadow(
                elevation = 4.dp,
                spotColor = Color.Black.copy(alpha = 0.1f),
                ambientColor = Color.Black.copy(alpha = 0.1f)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo and Brand Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.ic_splash), // Replace with your logo resource
                    contentDescription = "Smart Nutrition Logo",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
                
                // Brand Name
                Text(
                    text = "Smart Nutrition",
                    style = MobileTypography.headlineMedium,
                    color = Color(0xFF2E86FB) // Blue color from your image
                )
            }
            
            // User Profile Icon
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD5E7FB)) // Light blue background
                    .clickable { onProfileClick() } // Tambahkan clickable modifier
            ) {
                // You can replace this with an actual user profile image if available
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User Profile",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmartNutritionTopBarPreview() {
    SmartNutritionTopBar()
}