package com.example.smartnutrition.presentation.detailNutrition



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.presentation.detailNutrition.components.NutritionFullIndicatorCard
import com.example.smartnutrition.presentation.detailNutrition.components.NutritionFactsCard
import com.example.smartnutrition.presentation.detailNutrition.components.NutrientInfo
import com.example.smartnutrition.ui.theme.MobileTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNutritionScreen(
    viewModel: DetailNutritionViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    label: String,
    navigateToHome: () -> Unit // Tambahkan parameter untuk navigasi ke home
) {
    val state by viewModel.state.collectAsState()
    
    // Tambahkan LaunchedEffect untuk memantau status addSuccess
    LaunchedEffect(state.addSuccess) {
        if (state.addSuccess) {
            // Jika berhasil menambahkan data, navigasi ke home
            navigateToHome()
        }
    }
    
    // Tambahkan LaunchedEffect untuk memanggil getNutritionByLabel
    LaunchedEffect(key1 = true) {
        viewModel.getNutritionByLabel(label)
    }
    val sampleNutrients = listOf(
        NutrientInfo("Water", "${state.nutritionData?.data?.water}g"),
        NutrientInfo("Energy (Atwater General Factors)", "${state.nutritionData?.data?.energy?.atwater}kcal"),
        NutrientInfo("Energy (Atwater Specific Factors)", "${state.nutritionData?.data?.energy?.specific}kcal"),
        NutrientInfo("Nitrogen", "${state.nutritionData?.data?.nitrogen}g"),
        NutrientInfo("Minerals:", ""),
        NutrientInfo("Calcium, Ca", "${state.nutritionData?.data?.minerals?.calcium}mg", true),
        NutrientInfo("Iron, Fe", "${state.nutritionData?.data?.minerals?.iron}mg", true),
        NutrientInfo("Magnesium, Mg", "${state.nutritionData?.data?.minerals?.magnesium}g", true),
        NutrientInfo("Phosphorus, P", "${state.nutritionData?.data?.minerals?.phosphorus}mg", true),
        NutrientInfo("Potassium, K", "${state.nutritionData?.data?.minerals?.potassium}mg", true),
        NutrientInfo("Sodium, Na", "${state.nutritionData?.data?.minerals?.sodium}mg", true),
        NutrientInfo("Zinc, Zn", "${state.nutritionData?.data?.minerals?.zinc}mg", true),
        NutrientInfo("Copper, Cu", "${state.nutritionData?.data?.minerals?.copper}mg", true),
        NutrientInfo("Manganese, Mn", "${state.nutritionData?.data?.minerals?.manganese}mg", true)
    )




    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                ),
                title = {
                    Text(
                        "Detail Nutrisi",
                        style = MobileTypography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                modifier = Modifier.border(
                    width = 0.5.dp,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addNutrition(
                        userId = state.id.toString(),
                        fruitLabel = label,
                        quantity = 1
                    )
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Data"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally // Tambahkan ini untuk center alignment
        ) {
            Text(
                text = state.nutritionData?.data?.name.toString(),
                style = MobileTypography.headlineLarge.copy(fontSize = 32.sp),
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center // Tambahkan ini untuk center text
            )
            Text(
                text = "Informasi Nutrisi",
                style = MobileTypography.labelSmall,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center // Tambahkan ini untuk center text
            )
            state.nutritionData?.data?.let {
                NutritionFullIndicatorCard(
                    modifier = Modifier.padding(bottom = 16.dp),
                    kalori = it.kalori,
                    karbohidrat = it.karbohidrat,
                    protein = it.protein,
                    lemak = it.lemak
                )
            }
            NutritionFactsCard(
                nutrients = sampleNutrients,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }

}