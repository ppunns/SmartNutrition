package com.example.smartnutrition.presentation.detailNutrition.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Alignment
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.MobileTypography


data class NutrientInfo(
    val name: String,
    val value: String,
    val isSubItem: Boolean = false
)

@Composable
fun NutritionDetailCard(
    modifier: Modifier = Modifier,
    nutritionDetails: List<Pair<String, String>>
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            nutritionDetails.forEach { (label, value) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = label,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    Text(
                        text = value,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                }
                Divider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NutritionDetailCardPreview() {
    NutritionDetailCard(
        nutritionDetails = listOf(
            "Water" to "25g",
            "Energy (Atwater General Factors)" to "4.4g",
            "Energy (Atwater Specific Factors)" to "0.5",
            "Nitrogen" to "8.4mg",
            "Protein" to "195mg",
            "Total lipid (fat)" to "195mg",
            "Ash" to "195mg",
            "Carbohydrate, by difference" to "195mg",
            "Calcium, Ca" to "195mg",
            "Iron, Fe" to "195mg"
        )
    )
}






@Composable
fun NutritionFactsCard(
    nutrients: List<NutrientInfo>,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ){
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            nutrients.forEachIndexed { index, nutrient ->
                if (nutrient.name == "Carbohydrates:" || nutrient.name == "Minerals:") {
                    // Section headers
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Text(
                        text = nutrient.name,
                        style = MobileTypography.titleMedium.copy(fontSize = 12.sp),
                        fontWeight = FontWeight.Bold
                    )
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
                } else {
                    // Regular nutrient items
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = if (nutrient.isSubItem) 16.dp else 0.dp,
                                top = 7.dp,
                                bottom = 7.dp
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = nutrient.name,
                            style = MobileTypography.titleMedium.copy(fontSize = 12.sp),
                            color = if (nutrient.isSubItem) Color.Gray else Color.Black
                        )
                        Text(
                            text = nutrient.value,
                            style = MobileTypography.titleMedium.copy(fontSize = 12.sp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                    if (index < nutrients.size - 1 &&
                        !nutrients[index + 1].name.endsWith(":") &&
                        !nutrients[index + 1].isSubItem) {
                        Divider(
                            color = Color.LightGray,
                            thickness = 0.5.dp
                        )
                    }
                }
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun NutritionFactsPreview() {
    val sampleNutrients = listOf(
        NutrientInfo("Water", "85.6g"),
        NutrientInfo("Energy (Atwater General Factors)", "3kcal"),
        NutrientInfo("Energy (Atwater Specific Factors)", "0kcal"),
        NutrientInfo("Nitrogen", "2.8g"),
        NutrientInfo("Minerals:", ""),
        NutrientInfo("Calcium, Ca", "6mg", true),
        NutrientInfo("Iron, Fe", "0.12mg", true),
        NutrientInfo("Magnesium, Mg", "5g", true),
        NutrientInfo("Phosphorus, P", "11mg", true),
        NutrientInfo("Potassium, K", "107mg", true),
        NutrientInfo("Sodium, Na", "1mg", true),
        NutrientInfo("Zinc, Zn", "0.04mg", true),
        NutrientInfo("Copper, Cu", "0.027mg", true),
        NutrientInfo("Manganese, Mn", "0.035mg", true)
    )

    MaterialTheme {
        NutritionFactsCard(
            nutrients = sampleNutrients,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}