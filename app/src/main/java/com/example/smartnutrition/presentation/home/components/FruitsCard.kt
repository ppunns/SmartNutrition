package com.example.smartnutrition.presentation.home.components

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.smartnutrition.R
import com.example.smartnutrition.domain.model.Article
import com.example.smartnutrition.domain.model.Source
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.Natural100
import com.example.smartnutrition.ui.theme.Natural300
import com.example.smartnutrition.ui.theme.Natural50
import com.example.smartnutrition.ui.theme.SmartNutritionTheme




@Composable
fun FruitsCard(
    modifier: Modifier = Modifier,
    article: Article,
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
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = article.title,
                    modifier = Modifier.fillMaxSize(),  // Fill the box
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = article.title,
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
                    text = article.publishedAt,
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


@Preview(showBackground = true)
@Composable
fun FruitsCardPreview() {
    SmartNutritionTheme {
        FruitsCard(
            article = Article(
                author = "John Doe",
                content = "Sample content for preview",
                description = "Sample description",
                publishedAt = "2024-01-01",
                source = Source(id = "sample-id", name = "Sample Source"),
                title = "Sample Fruit Article",
                url = "",
                urlToImage = ""
            )
        )
    }
}



@Composable
fun FruitCard2(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Fruit image or fallback colored circle
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF8BB3FF) )
            ) {

            }

            Spacer(modifier = Modifier.width(16.dp))

            // Fruit information
            Column {
                Text(
                    text = "Mengga",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "asdasda",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
