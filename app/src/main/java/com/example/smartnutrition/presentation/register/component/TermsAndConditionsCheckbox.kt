package com.example.smartnutrition.presentation.register.component

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.ui.theme.MobileTypography
import com.example.smartnutrition.ui.theme.Natural100
import com.example.smartnutrition.ui.theme.Natural200


@Composable
fun TermsAndConditionsCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClicked: () -> Unit,
    onPrivacyPolicyClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Custom rounded checkbox
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
                .background(if (isChecked) MaterialTheme.colorScheme.primary else Color.Transparent)
                .clickable { onCheckedChange(!isChecked) },
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        // Creating text with clickable spans
        val annotatedText = buildAnnotatedString {

            withStyle(
                style = MobileTypography.labelLarge.toSpanStyle().copy(color = Color.Gray)
            ){
                append("I've read and agree with the ")
            }


            // Terms and Conditions (styled and clickable)
            withStyle(
                style = MobileTypography.labelSmall.toSpanStyle().copy(color = MaterialTheme.colorScheme.primary)
            ) {
                append("Terms and Conditions")
            }
            withStyle(
                style = MobileTypography.labelLarge.toSpanStyle().copy(color = Color.Gray)
            ){
                append(" and the ")
            }
            withStyle(
                style = MobileTypography.labelSmall.toSpanStyle().copy(color = MaterialTheme.colorScheme.primary)
            ) {
                append("Privacy Policy")
            }
            withStyle(
                style = MobileTypography.labelLarge.toSpanStyle().copy(color = Color.Gray)
            ){
                append(".")
            }
        }

        Text(
            text = annotatedText,
            modifier = Modifier.clickable {
                // This makes the entire text clickable, but in practice
                // you would want to determine which part was clicked
                onCheckedChange(!isChecked)
            }
        )
    }
}