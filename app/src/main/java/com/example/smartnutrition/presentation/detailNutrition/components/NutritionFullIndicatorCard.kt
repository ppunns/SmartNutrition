package com.example.smartnutrition.presentation.detailNutrition.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.presentation.common.NutritionInfo
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard

import com.example.smartnutrition.ui.theme.Blue50


@Composable
fun NutritionFullIndicatorCard(
    modifier: Modifier = Modifier,
    kalori: Int,
    karbohidrat: Double,
    protein: Double,
    lemak: Double
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blue50
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                NutritionInfo(
                    value = kalori,
                    maxValue = 4000,
                    unit = "kcal",
                    label = "Kalori", 
                    progressColor = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
                NutritionInfo(
                    value = protein.toInt(),
                    maxValue = 100,
                    unit = "g",
                    label = "Protein",
                    progressColor = Color.Green,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                NutritionInfo(
                    value = karbohidrat.toInt(),
                    maxValue = 100,
                    unit = "g",
                    label = "Karbohidrat",
                    progressColor = Color.Yellow,
                    modifier = Modifier.weight(1f) //
                )
                NutritionInfo(
                    value = lemak.toInt(),
                    maxValue = 100,
                    unit = "g",
                    label = "Lemak",
                    progressColor = Color.Red,
                    modifier = Modifier.weight(1f) //
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NutritionIndicatorCardPreview() {
//    NutritionFullIndicatorCard(
//        totalKalori = 582,
//        totalKarbohidrat = 26,
//        totalProtein = 58,
//        totalLemak = 28
//    )
//}