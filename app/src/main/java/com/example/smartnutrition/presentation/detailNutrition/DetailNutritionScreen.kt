package com.example.smartnutrition.presentation.detailNutrition



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.presentation.detailNutrition.components.NutritionDetailCard
import com.example.smartnutrition.presentation.detailNutrition.components.NutritionFullIndicatorCard
import com.example.smartnutrition.presentation.detailNutrition.components.NutritionFactsCard
import com.example.smartnutrition.presentation.detailNutrition.components.NutrientInfo
import com.example.smartnutrition.ui.theme.MobileTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNutritionScreen(
    onNavigateBack: () -> Unit,
) {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Detail Nutrisi",
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
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Appel",
                style = MobileTypography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Informasi Nutrisi",
                style = MobileTypography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            NutritionFullIndicatorCard(
                totalKalori = 582,
                totalKarbohidrat = 26,
                totalProtein = 58,
                totalLemak = 28,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            NutritionFactsCard(
                nutrients = listOf(
                    NutrientInfo("Water", "83.6g"),
                    NutrientInfo("Energy (Atwater General Factors)", "65kcal"),
                    NutrientInfo("Energy (Atwater Specific Factors)", "58kcal"),
                    NutrientInfo("Nitrogen", "0.02g"),
                    NutrientInfo("Protein", "0.15g"),
                    NutrientInfo("Total lipid (fat)", "0.16g"),
                    NutrientInfo("Ash", "0.43g"),
                    NutrientInfo("Carbohydrates:", ""),
                    NutrientInfo("Carbohydrate, by difference", "15.4g", true),
                    NutrientInfo("Carbohydrate, by summation", "15.4g", true),
                    NutrientInfo("Minerals:", ""),
                    NutrientInfo("Calcium, Ca", "6mg", true),
                    NutrientInfo("Iron, Fe", "0.02mg", true),
                    NutrientInfo("Magnesium, Mg", "4.7g", true),
                    NutrientInfo("Phosphorus, P", "10mg", true),
                    NutrientInfo("Potassium, K", "104mg", true),
                    NutrientInfo("Sodium, Na", "1mg", true),
                    NutrientInfo("Zinc, Zn", "0.02mg", true),
                    NutrientInfo("Copper, Cu", "0.033mg", true),
                    NutrientInfo("Manganese, Mn", "0.033mg", true)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun DetailNutritionScreenPreview() {
    MaterialTheme {
        Surface {
            DetailNutritionScreen(
                onNavigateBack = {}
            )
        }
    }
}