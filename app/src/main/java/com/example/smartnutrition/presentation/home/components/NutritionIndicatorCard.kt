package com.example.smartnutrition.presentation.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.presentation.common.NutritionInfo

import com.example.smartnutrition.ui.theme.Blue50

@Composable
fun NutritionIndicatorCard(
    modifier: Modifier = Modifier,
    totalKarbohidrat: Int,
    totalProtein: Int,
    totalLemak: Int
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
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NutritionInfo(
                value = totalKarbohidrat,
                maxValue = 100,
                unit = "kcal",
                label = "Karbohidrat",
                progressColor = Color.Blue,
                modifier = Modifier.weight(1f) // Pastikan weight digunakan
            )

            NutritionInfo(
                value = totalProtein,
                unit = "g",
                label = "Protein",
                maxValue = 100,
                progressColor = Color(0xFF99FFB3),
                modifier = Modifier.weight(1f) // Pastikan weight digunakan
            )

            NutritionInfo(
                value = totalLemak,
                unit = "g",
                label = "Lemak",
                maxValue = 100,
                progressColor = Color(0xFFFF9999),
                modifier = Modifier.weight(1f) // Pastikan weight digunakan
            )
        }
    }
}



//@Composable
//private fun NutrientInfo(
//    value: Int,
//    unit: String,
//    label: String,
//    progress: Float,
//    progressColor: Color,
//    modifier: Modifier = Modifier
//) {
//    var isAnimated by remember { mutableStateOf(false) }
//
//    val animatedProgress by animateFloatAsState(
//        targetValue = if (isAnimated) progress else 0f,
//        animationSpec = tween(1000, easing = FastOutSlowInEasing)
//    )
//
//    val animatedValue by animateIntAsState(
//        targetValue = if (isAnimated) value else 0,
//        animationSpec = tween(1000, easing = FastOutSlowInEasing)
//    )
//
//    LaunchedEffect(Unit) {
//        isAnimated = true
//    }
//
//    Column(
//        horizontalAlignment = Alignment.Start,
//        modifier = modifier
//    ) {
//        Row(
//            verticalAlignment = Alignment.Bottom
//        ) {
//            Text(
//                text = "$animatedValue",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = unit,
//                fontSize = 13.sp,
//                color = Color.Gray,
//                modifier = Modifier.padding(start = 4.dp, bottom = 2.dp)
//            )
//        }
//
//        Text(
//            text = label,
//            fontSize = 13.sp,
//            color = Color.Gray
//        )
//
//        Spacer(modifier = Modifier.height(5.dp))
//
//        Box(
//            modifier = Modifier
//                .width(80.dp)
//                .height(5.dp)
//                .clip(RoundedCornerShape(2.dp))
//                .background(Color(0xFFE0E0E0))
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .fillMaxWidth(animatedProgress)
//                    .clip(RoundedCornerShape(2.dp))
//                    .background(progressColor)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Text(
//            text = "${(progress * 100).toInt()}%",
//            fontSize = 10.sp,
//            color = Color.Gray
//        )
//    }
//}



@Preview(showBackground = true)
@Composable
fun NutritionIndicatorCardPreview() {
    NutritionIndicatorCard(
        totalKarbohidrat = 100,
        totalProtein = 100,
        totalLemak = 100
    )
}