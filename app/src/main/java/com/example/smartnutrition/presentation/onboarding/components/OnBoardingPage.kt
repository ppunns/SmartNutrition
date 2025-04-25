package com.example.smartnutrition.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartnutrition.R
import com.example.smartnutrition.presentation.Dimens.MediumPadding1
import com.example.smartnutrition.presentation.Dimens.MediumPadding2
import com.example.smartnutrition.presentation.onboarding.Page
import com.example.smartnutrition.ui.theme.MobileTypography
import com.example.smartnutrition.ui.theme.SmartNutritionTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(327.dp)
                .height(216.dp),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(MediumPadding2))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2),
            text = page.title,
            style = MobileTypography.headlineLarge,
            color = colorResource(id = R.color.display_small),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2),
            text = page.description,
            style = MobileTypography.labelLarge,
            color = colorResource(id = R.color.text_medium),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    SmartNutritionTheme {
        OnBoardingPage(
            page = Page(
                title = "Selamat Datang di SmartNutrition",
                description = "Aplikasi pintar untuk mencatat dan melacak apa yang kamu konsumsi setiap hari—cukup dengan scan!",
                image = R.drawable.image
            )
        )
    }
}

