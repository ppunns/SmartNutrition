package com.example.smartnutrition.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.theme.BlueGray

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = BlueGray
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(times = pagerSize) { page ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        color = if (page <= selectedPage) selectedColor else unselectedColor.copy(alpha = 0.5f)
                    )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PagerIndicatorPreview() {
    MaterialTheme {
        PagerIndicator(
            modifier = Modifier.padding(horizontal = 16.dp),
            pagerSize = 3,
            selectedPage = 1
        )
    }
}