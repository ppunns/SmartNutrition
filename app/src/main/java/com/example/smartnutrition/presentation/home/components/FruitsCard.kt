package com.example.smartnutrition.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.smartnutrition.R
import com.example.smartnutrition.domain.model.Article
import com.example.smartnutrition.domain.model.Source
import com.example.smartnutrition.ui.theme.SmartNutritionTheme




@Composable
fun FruitsCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(11.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = article.title,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
