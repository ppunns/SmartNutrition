package com.example.smartnutrition.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.smartnutrition.domain.model.Item
import com.example.smartnutrition.ui.theme.Blue50


@Composable
fun FruitsCard(
    modifier: Modifier = Modifier,
    nutrition: Item,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
//            .background(Color(0xFF8BB3FF))
//            .height(120.dp)  // Added fixed height
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Blue50)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(49.dp)  // Adjusted size to fit smaller card
                    .clip(CircleShape)
            ) {
                // Tambahkan konfigurasi untuk Coil
                AsyncImage(
                    model = "",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    // Tambahkan konfigurasi caching
                    onLoading = { /* Tampilkan placeholder jika diperlukan */ },
                    onError = { /* Tampilkan error image jika diperlukan */ }
                )
            }

            Column {
                Text(
                    text = nutrition.fruitName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF1F2024),

                        ),
                    maxLines = 1,
                     overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = nutrition.timestamp,
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF494A50),

                        letterSpacing = 0.1.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


