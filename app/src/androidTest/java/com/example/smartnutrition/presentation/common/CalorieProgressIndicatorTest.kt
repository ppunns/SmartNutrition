package com.example.smartnutrition.presentation.common


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.smartnutrition.presentation.home.components.CalorieProgressIndicator
import org.junit.Rule
import org.junit.Test

class CalorieProgressIndicatorTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calorieProgressDisplaysCorrectly() {
        val currentCalories = 2082
        val targetCalories = 2925

        composeTestRule.setContent {
            CalorieProgressIndicator(
                currentCalories = currentCalories,
                targetCalories = targetCalories
            )
        }

        composeTestRule.onNodeWithText("Kalori").assertIsDisplayed()
        composeTestRule.onNodeWithText(currentCalories.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText("Of $targetCalories kcal").assertIsDisplayed()
    }
}