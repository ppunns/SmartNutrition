package com.example.smartnutrition.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.example.smartnutrition.domain.model.Article
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.smartnutrition.presentation.common.EmptyScreen
import com.example.smartnutrition.presentation.common.FruitsCardList
import com.example.smartnutrition.presentation.common.SmartNutritionTopBar
import com.example.smartnutrition.presentation.home.components.CalorieProgressCard
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import com.loc.newsapp.presentation.common.FruitsCardShimmerEffect

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate:(String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()  // Move statusBarsPadding here
    ) {
        SmartNutritionTopBar()
        
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),  // Removed statusBarsPadding from here
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CalorieProgressIndicator(
                    currentCalories = 3082,
                    targetCalories = 4000
                )
            }
            item {
                NutritionIndicatorCard()
            }
            item {
                Text(
                    text = "Riwayat Makanan",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            when {
                articles.loadState.refresh is LoadState.Loading -> {
                    items(10) {
                        FruitsCardShimmerEffect(
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
                articles.loadState.refresh is LoadState.Error -> {
                    item {
                        EmptyScreen(error = (articles.loadState.refresh as LoadState.Error))
                    }
                }
                else -> {
                    items(articles.itemCount) { index ->
                        articles[index]?.let { article ->
                            FruitsCard(
                                article = article,
                                onClick = { navigate(article.url) }
                            )
                        }
                    }
                }
            }
        }
    }
}

