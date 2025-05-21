package com.example.smartnutrition.presentation.home.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.smartnutrition.ui.theme.Blue100
import com.example.smartnutrition.ui.theme.Blue300
import com.example.smartnutrition.ui.theme.Blue50
import com.example.smartnutrition.ui.theme.Blue500


@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    items: List<String> = listOf("Harian", "Bulanan"),
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color(0xFF006FFD).copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Blue50
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            color = if (selectedIndex == index) Color.White else Color.Transparent
                        )
                        .clickable {
                            onItemSelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = if (selectedIndex == index) FontWeight.Medium else FontWeight.Normal,
                        color = if (selectedIndex == index) Color.Black else Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SegmentedControlPreview() {
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        SegmentedControl(
            onItemSelected = { selectedTab = it }
        )

        // Konten berdasarkan tab yang dipilih
        when (selectedTab) {
            0 -> {
                // Tampilan Harian
                Text("Konten Harian", modifier = Modifier.padding(16.dp))
            }
            1 -> {
                // Tampilan Bulanan
                Text("Konten Bulanan", modifier = Modifier.padding(16.dp))
            }
        }
    }
}