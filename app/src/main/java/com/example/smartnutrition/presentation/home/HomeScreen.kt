package com.example.smartnutrition.presentation.home

import FruitsCardShimmerEffect
import android.Manifest
import android.graphics.Paint.Style
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnutrition.R
import com.example.smartnutrition.presentation.common.PrimaryFloatingActionButton
import com.example.smartnutrition.presentation.common.SharedViewModel
import com.example.smartnutrition.presentation.common.SmartNutritionTopBar
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import com.example.smartnutrition.presentation.home.components.SegmentedControl
import com.example.smartnutrition.presentation.navgraph.Route
import com.example.smartnutrition.ui.theme.Blue500
import com.example.smartnutrition.ui.theme.MobileTypography
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

//    val cameraPermissionState: PermissionState =
//        rememberPermissionState(Manifest.permission.CAMERA)

    var showProteinTargetDialog by remember { mutableStateOf(false) }
    val proteinTarget by viewModel.proteinTarget.collectAsState()

    LaunchedEffect(proteinTarget) {
        if (proteinTarget == 0) {
            showProteinTargetDialog = true
        }
    }

    if (showProteinTargetDialog) {
        AlertDialog(
            onDismissRequest = { showProteinTargetDialog = false },
            containerColor = Color.White,
            properties = DialogProperties(
                usePlatformDefaultWidth = false // Menonaktifkan lebar default platform
            ),
            modifier = Modifier
                .fillMaxWidth(0.85f),
            shape = RoundedCornerShape(12.dp),
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Atur Target Kalori Harianmu",
                        style = MobileTypography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Untuk melacak progress harian dengan lebih akurat, yuk atur target kalorimu sekarang.",
                        style = MobileTypography.labelLarge,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = { showProteinTargetDialog = false },
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Blue500)
                    ) {
                        Text("Nanti Saja", color = Blue500, style = MobileTypography.labelLarge)
                    }
                    Button(
                        onClick = {
                            showProteinTargetDialog = false
                            navigate(Route.ProfileScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue500
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Atur Sekarang", color = Color.White, style = MobileTypography.labelLarge)
                    }
                }
            },
            dismissButton = null
        )
    }

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
                        LaunchedEffect(Unit) {
                            viewModel.updateHistoryType(false)
                        }
                        DailyView(viewModel)
                    }
                    1 -> {
                        LaunchedEffect(Unit) {
                            viewModel.updateHistoryType(true)
                        }
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
//                    if (cameraPermissionState.status.isGranted) {
                        navigate(Route.CameraScanning.route)
//                    } else {
//                        cameraPermissionState.launchPermissionRequest()
//                    }
                },
                icon = R.drawable.scanicons,
                contentDescription = "Scan Buah"
            )
        }
    }
}

@Composable
fun DailyView(viewModel: HomeViewModel) {
    val proteinTarget by viewModel.proteinTarget.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item {
            CalorieProgressIndicator(
                currentCalories = viewModel.historyState.value.data?.data?.totalKalori?:0,
                proteinTarget = proteinTarget ,
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
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
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
                    viewModel.historyState.value.data?.data?.items?.let { items ->
                        items(items.size) { index ->
                            Log.d("NutritionItem", "Image URL = ${items[index].imageUrl}")
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
