package com.example.smartnutrition.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.theme.Blue200
import com.example.smartnutrition.ui.theme.Blue50

@Composable
fun BlueCircleElement(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier // Mengubah tinggi menjadi setengah layar
            .alpha(0.55f)
    ) {
        Canvas(modifier = Modifier.fillMaxHeight()
            .height(800.dp)
            .padding(10.dp)
            .fillMaxWidth()) {
            // Menggambar lingkaran luar
            drawCircle(
                color = Blue50,
                radius = size.minDimension / 1.95f,
                style = Stroke(width = 3.5f, cap = StrokeCap.Round)
            )

            // Menggambar lingkaran biru solid
            // Draw background circle
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Blue50,
                        Blue50
                    )
                ),
                radius = size.minDimension / 2.5f,
            )

            // Draw border circle
            drawCircle(
                color = Blue200,
                radius = size.minDimension / 2.5f,
                style = Stroke(
                    width = 2f,
                    cap = StrokeCap.Round
                )
            )
        }
    }
}