package com.example.smartnutrition.presentation.home

import FruitsCardShimmerEffect
import android.Manifest
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnutrition.R
import com.example.smartnutrition.presentation.common.PrimaryFloatingActionButton
import com.example.smartnutrition.presentation.common.SmartNutritionTopBar
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import com.example.smartnutrition.presentation.home.components.SegmentedControl
import com.example.smartnutrition.presentation.navgraph.Route
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    DataNutrition: HomeState,
    navigate: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val cameraPermissionState: PermissionState =
        rememberPermissionState(Manifest.permission.CAMERA)

    Box(modifier = Modifier.fillMaxSize()) {
        // Tabs for switching between Daily and Monthly view
        var selectedPeriodTab by remember { mutableStateOf(0) }
        val periodTabs = listOf("Harian", "Bulanan")
        val pagerState = rememberPagerState(pageCount = { periodTabs.size })

        // Synchronize selected tab with pager
        LaunchedEffect(selectedPeriodTab) {
            pagerState.animateScrollToPage(selectedPeriodTab)
        }

        LaunchedEffect(pagerState.currentPage) {
            selectedPeriodTab = pagerState.currentPage
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SmartNutritionTopBar(
                onProfileClick = {
                    navigate(Route.ProfileScreen.route)
                }
            )

            // Period selector (Daily/Monthly)
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                SegmentedControl(
                    items = periodTabs,
                    selectedIndex = selectedPeriodTab,
                    onItemSelected = { newTab ->
                        selectedPeriodTab = newTab
                    }
                )
            }

            // Horizontal pager for Daily/Monthly views
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                pageSpacing = 16.dp,
                userScrollEnabled = true
            ) { page ->
                when (page) {
                    0 -> {
                        // Daily View
                        DailyView(viewModel)
                    }
                    1 -> {
                        // Monthly View
                        DailyView(viewModel)
                    }
                }
            }
        }

        // Floating Action Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            PrimaryFloatingActionButton(
                onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        navigate(Route.CameraScanning.route)
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                },
                icon = R.drawable.scanicons,
                contentDescription = "Scan Buah"
            )
        }
    }
}

@Composable
fun DailyView(viewModel: HomeViewModel) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item {
            CalorieProgressIndicator(
                currentCalories = viewModel.historyState.value.data?.data?.totalKalori?:0,
                targetCalories = 4000
            )
        }

        item {
            NutritionIndicatorCard(
                totalKarbohidrat = viewModel.historyState.value.data?.data?.totalKarbohidrat?.toInt() ?: 0,
                totalProtein = viewModel.historyState.value.data?.data?.totalProtein?.toInt() ?: 0,
                totalLemak = viewModel.historyState.value.data?.data?.totalLemak?.toInt() ?: 0
            )
        }

        item {
            Text(
                text = "Riwayat Makanan",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Food history items
        when {
            viewModel.historyState.value.isLoading -> {
                items(3) {
                    FruitsCardShimmerEffect(
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            viewModel.historyState.value.error != null -> {
                item {
                    Text(
                        text = viewModel.historyState.value.error ?: "",
                        color = Color.Blue,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            else -> {
                if (viewModel.historyState.value.data?.data?.items.isNullOrEmpty()) {
                    // Tampilkan pesan ketika tidak ada data
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Belum ada riwayat makanan hari ini.\nSilakan scan makanan Anda untuk memulai!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    // Tampilkan list jika ada data
                    viewModel.historyState.value.data?.data?.items?.let { items ->
                        items(items.size) { index ->
                            FruitsCard(
                                nutrition = items[index],
                                onClick = null,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}