package com.example.smartnutrition.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.smartnutrition.R


val Poppins = FontFamily(
    fonts = listOf(
        Font(R.font.inter_regular, FontWeight.Normal),
        Font(R.font.inter_bold, FontWeight.Bold),
        Font(R.font.inter_semibold, FontWeight.SemiBold),
        Font(R.font.inter_extrabold, FontWeight.ExtraBold)
    )
)

// Set of Material typography styles to start with
// body is "Text" in the Figma Design
// label small is xsmall in Figma Design
val Typography = Typography(
    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),

)


// Gaya tipografi tambahan yang dioptimalkan untuk mobile
val MobileTypography = Typography(
    // H1 - Extra bold / 24
    headlineLarge = TextStyle(
        fontSize = 26.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    // H2 - Extra bold / 18
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // H3 - Extra bold / 16
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // H4 - Bold / 14
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // H5 - Bold / 12
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp,
        letterSpacing = 0.15.sp
    ),
    // Body XL - Regular / 18
    bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 27.sp,
        letterSpacing = 0.15.sp
    ),
    // Body L - Regular / 16
    bodyMedium = TextStyle(
        fontSize = 18.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp
    ),
    // Body M - Regular / 14
    bodySmall = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
        letterSpacing = 0.4.sp
    ),
    // Body S - Regular / 12
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
    ),
    // Action L - Semi Bold / 14
    labelMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    // Action M - Semi Bold / 12
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    )
)
