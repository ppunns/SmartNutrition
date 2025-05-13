package com.example.smartnutrition.presentation.home.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.smartnutrition.R
import com.example.smartnutrition.domain.model.Data
import com.example.smartnutrition.domain.model.Item
import com.example.smartnutrition.presentation.login.LoginScreen
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.MobileTypography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun FruitsCard(
    modifier: Modifier = Modifier,
    nutrition: Item,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
                    .size(49.dp)
                    .clip(CircleShape)
            ) {
                var isError by remember { mutableStateOf(false) }
                
                if (isError) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Error Image",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    AsyncImage(
                        model = "https://storage.googleapis.com/image-buah/pir.jpg?GoogleAccessId=firestore-accces%40smart-nutrition-app-457602.iam.gserviceaccount.com&Expires=1747212141&Signature=ox%2FPXzR25A7w3TWnYhTa2np2eYV71WE457Dt%2FPFjMyO98cLLCNVBsDReGrQsEJPyDnhQeq4PSghn%2Bqv5IVSY0%2BJTWGt7K8kw0JEDSmM3LrKCprUfAhuE5DMcyS2NOXyMXnCBNe4gH7dP7%2BKHhJ7Q7sg6SjhDdcaSYf3T9FYQnLDoHkniVWrrXFUMy4RYaxd6mVe4cAIhn5wM0YzUs39wejMsPMbpdgbgKRqElKAJm7Vz9DRnRi1lZytgMj9dgvkGm3D%2BfxAzFTJcYTHjcLM4oNmseelNINizqookc%2BuMYoLaCwOh1lew%2BTsVkDxkh3rexECYrf7M3WyfBaTYludaMw%3D%3D",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        onLoading = { /* Tampilkan placeholder jika diperlukan */ },
                        onError = {
                            Log.e("AsyncImage", "Error loading image: ${nutrition.imageUrl}")
                            Log.d("NutritionImage", "URL: ${nutrition.imageUrl}")
                            isError = true
                        }
                    )
                }
            }

            Column {
                Text(
                    text = nutrition.fruitName,
                    style = MobileTypography.titleMedium,
                    maxLines = 1,
                     overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = nutrition.timestamp,
                    style = MobileTypography.labelLarge.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


