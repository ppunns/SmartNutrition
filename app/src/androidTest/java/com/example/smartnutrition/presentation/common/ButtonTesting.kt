package com.example.smartnutrition.presentation.common

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ButtonTesting {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun primaryButtonDisplaysCorrectly() {
        val buttonText = "Simpan"

        composeTestRule.setContent {
            PrimaryButton(
                text = buttonText,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText(buttonText).assertIsDisplayed()
    }

    @Test
    fun secondaryButtonDisplaysCorrectly() {
        val buttonText = "Batal"

        composeTestRule.setContent {
            SecondaryButton(
                text = buttonText,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithText(buttonText).assertIsDisplayed()
    }
}