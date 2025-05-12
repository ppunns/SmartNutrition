package com.example.smartnutrition.presentation.common


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.smartnutrition.presentation.home.components.NutritionIndicatorCard
import org.junit.Rule
import org.junit.Test

class NutritionIndicatorCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun nutritionIndicatorDisplaysCorrectly() {
        // Verifikasi nilai karbohidrat
        composeTestRule.onNodeWithText("582").assertIsDisplayed()
        composeTestRule.onNodeWithText("Karbohidrat").assertIsDisplayed()

        // Verifikasi nilai protein
        composeTestRule.onNodeWithText("58").assertIsDisplayed()
        composeTestRule.onNodeWithText("Protein").assertIsDisplayed()

        // Verifikasi nilai lemak
        composeTestRule.onNodeWithText("28").assertIsDisplayed()
        composeTestRule.onNodeWithText("Lemak").assertIsDisplayed()
    }
}