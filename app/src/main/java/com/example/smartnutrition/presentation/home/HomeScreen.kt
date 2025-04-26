package com.example.smartnutrition.presentation.home

import FruitsCardShimmerEffect
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.example.smartnutrition.domain.model.Article
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import com.example.smartnutrition.presentation.common.EmptyScreen
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.smartnutrition.R
import com.example.smartnutrition.domain.model.Source
import com.example.smartnutrition.presentation.camera.CameraScreen
import com.example.smartnutrition.presentation.common.FruitsCardList
import com.example.smartnutrition.presentation.common.PrimaryFloatingActionButton
import com.example.smartnutrition.presentation.common.SmartNutritionTopBar
import com.example.smartnutrition.presentation.home.components.SegmentedControl
import com.example.smartnutrition.presentation.navgraph.Route
import com.example.smartnutrition.ui.theme.Blue100
import com.example.smartnutrition.ui.theme.Blue500
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalPermissionsApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(android.Manifest.permission.CAMERA)
    
    Box(modifier = Modifier.fillMaxSize()) {
        var selectedTab by remember { mutableStateOf(0) }
        val pagerState = rememberPagerState(pageCount = { 2 })
        
        // Menambahkan LaunchedEffect untuk sinkronisasi state
        LaunchedEffect(selectedTab) {
            pagerState.animateScrollToPage(selectedTab)
        }
        
        LaunchedEffect(pagerState.currentPage) {
            selectedTab = pagerState.currentPage
        }
        Column(
            modifier = Modifier
                .fillMaxSize() // Tambahkan padding top untuk memberikan ruang bagi SegmentedControl
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                pageSpacing = 16.dp
            ) { page ->
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp),  // Memperbesar jarak antar item
                    modifier = Modifier.padding(horizontal = 24.dp)  // Memperbesar padding horizontal
                ) {
                    when (page) {
                        0 -> {
                            item {
                                Spacer(modifier = Modifier.height(150.dp))
                            }
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
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            // Artikel / konten harian
                            when {
                                articles.loadState.refresh is LoadState.Loading -> {
                                    items(3) {
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
                                    items(
                                        count = minOf(articles.itemCount, 20),
                                        key = { index -> articles[index]?.url ?: index }
                                    ) { index ->
                                        articles[index]?.let { article ->
                                            FruitsCard(
                                                article = article,
                                                onClick = { navigate(article.url) },
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        1 -> {
                            item {
                                Spacer(modifier = Modifier.height(150.dp))
                            }
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
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            // Artikel / konten harian
                            when {
                                articles.loadState.refresh is LoadState.Loading -> {
                                    items(3) {
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
                                    items(
                                        count = minOf(articles.itemCount, 20),
                                        key = { index -> articles[index]?.url ?: index }
                                    ) { index ->
                                        articles[index]?.let { article ->
                                            FruitsCard(
                                                article = article,
                                                onClick = { navigate(article.url) },
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ){
            SmartNutritionTopBar()
        }
        // Menghapus SmartNutritionTopBar yang duplikat
        
        // Floating SegmentedControl
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 24.dp)
                .padding(top = 110.dp)
        ) {
            SegmentedControl(
                selectedIndex = selectedTab,  // Menggunakan selectedTab
                onItemSelected = { newTab -> 
                    selectedTab = newTab  // Update selectedTab
                }
            )
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
                .padding(horizontal = 24.dp, vertical = 24.dp)  // Sesuaikan padding
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}



//@Composable
//private fun MainContent(
//    hasPermission: Boolean,
//    onRequestPermission: () -> Unit
//) {
//    if (hasPermission) {
//        CameraScreen(
//            navigate = {}
//        )
//    } else {
////        NoPermissionScreen(onRequestPermission)
//    }
//}

//@Preview
//@Composable
//private fun Preview_MainContent() {
//    MainContent(
//        hasPermission = true,
//        onRequestPermission = {}
//    )
//}

//@Preview(showBackground = true)
//@Composable
//private fun Preview_HomeScreen() {
//    val fakeArticles = flowOf(
//        PagingData.from(
//            listOf(
//                Article(
//                    author = "John Doe",
//                    source = Source(id = "nutrition-news", name = "Nutrition News"),
//                    title = "Apel",
//                    description = "Buah apel segar",
//                    url = "https://example.com",
//                    urlToImage = "",
//                    publishedAt = "2024-03-12T08:15:00Z",
//                    content = "Konten artikel tentang apel",
//                )
//            )
//        )
//    ).collectAsLazyPagingItems()
//
//    HomeScreen(
//        articles = fakeArticles,
//        navigate = {}
//    )
//}