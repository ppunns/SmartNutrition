package com.example.smartnutrition.presentation.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.ui.theme.Blue50

@Composable
fun CalorieProgressIndicator(
    currentCalories: Int,
    targetCalories: Int,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val strokeWidth = with(density) { 20.dp.toPx() }
    
    // Calculate progress percentage
    val progress = currentCalories.toFloat() / targetCalories

    // Single animation state for both progress and value
    val animatedProgress = remember { Animatable(0f) }

    // Start animation when the component is first displayed
    LaunchedEffect(currentCalories) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(
                durationMillis = 1500,
                easing = FastOutSlowInEasing
            )
        )
    }

    // Calculate the animated calorie value
    val animatedCalories = (animatedProgress.value * targetCalories).toInt()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(251.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blue50
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            Canvas(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxSize()
            ) {
                val diameter = minOf(size.width, size.height)
                val arcSize = Size(diameter, diameter)
                
                // Center the arc both horizontally and vertically
                val xOffset = (size.width - diameter) / 2
                val yOffset = (size.height - diameter) / 2

                val startAngle = 150f
                val sweepAngle = 240f

                // Background arc
                drawArc(
                    color = Color(0xFFDCE6FF),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    topLeft = Offset(xOffset, yOffset),
                    size = arcSize
                )

                // Progress arc
                drawArc(
                    color = Color(0xFF2D7CFF),
                    startAngle = startAngle,
                    sweepAngle = sweepAngle * animatedProgress.value,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    topLeft = Offset(xOffset, yOffset),
                    size = arcSize
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = 5.dp)
            ) {
                Text(
                    text = "Kalori",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$animatedCalories",  // Use animatedCalories instead of animatedValue
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Of $targetCalories kcal",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CalorieTrackerScreen() {
    var currentCalories by remember { mutableStateOf(0) }
    val targetCalories = 2925

    // Animation to increase calories for demo purposes
    LaunchedEffect(Unit) {
        currentCalories = 2082 // Set to final value with animation
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CalorieProgressIndicator(
                currentCalories = currentCalories,
                targetCalories = targetCalories
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalorieProgressPreview() {
    MaterialTheme {
        CalorieTrackerScreen()
    }
}

// Additional components you might want to include in a full app

@Composable
fun CalorieEntry(
    onAddCalories: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var calorieInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // UI for adding calories would go here
        // This could include TextField for input and buttons
    }
}

@Composable
fun MealListItem(
    mealName: String,
    calories: Int,
    time: String,
    modifier: Modifier = Modifier
) {
    // A component to display individual meals in a list
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Meal information would go here
    }
}