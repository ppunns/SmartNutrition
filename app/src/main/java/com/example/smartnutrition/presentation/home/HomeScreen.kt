package com.example.smartnutrition.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.example.smartnutrition.domain.model.Article
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import com.example.smartnutrition.presentation.common.EmptyScreen
import com.example.smartnutrition.presentation.common.FruitsCardList
import com.example.smartnutrition.presentation.common.SmartNutritionTopBar
import com.example.smartnutrition.presentation.home.components.CalorieProgressCard
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import com.loc.newsapp.presentation.common.FruitsCardShimmerEffect

// Add these imports
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartnutrition.R
import com.example.smartnutrition.presentation.camera.CameraScreen
import com.example.smartnutrition.presentation.common.PrimaryFloatingActionButton
import com.example.smartnutrition.presentation.navgraph.Route
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate:(String) -> Unit
) {
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    
    // Removed MainContent call here to avoid showing CameraScreen in HomeScreen
    
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
//            SmartNutritionTopBar()
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
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
        
        PrimaryFloatingActionButton(
            onClick = { 
                if (cameraPermissionState.status.isGranted) {
                    navigate(Route.CameraScanning.route)
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
            },
            icon = R.drawable.scanicons,
            contentDescription = "Scan Food",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter)
                .width(280.dp)
        )
    }
}

// Keeping this function for future use but not calling it from HomeScreen
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MainContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
) {
    if (hasPermission) {
        CameraScreen()
    } else {
//        NoPermissionScreen(onRequestPermission)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun Preview_MainContent() {
    MainContent(
        hasPermission = true,
        onRequestPermission = {}
    )
}
