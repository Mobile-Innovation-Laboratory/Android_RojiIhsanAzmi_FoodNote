package com.app.foodnote

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.app.foodnote.AuthViewModel
import com.app.foodnote.ui.screens.RegisterScreen
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registerScreen_displaysAllFields() {
        composeTestRule.setContent {
            RegisterScreen(authViewModel = AuthViewModel())
        }

        composeTestRule.onNodeWithText("Register Your Account").assertIsDisplayed()
        composeTestRule.onNodeWithText("Username").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Confirm Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Register").assertIsDisplayed()
    }

    @Test
    fun registerScreen_enterValidData_clicksRegister() {
        composeTestRule.setContent {
            RegisterScreen(authViewModel = AuthViewModel())
        }

        composeTestRule.onNodeWithText("Username").performTextInput("testUser")
        composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Confirm Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Register").performClick()
    }

    @Test
    fun registerScreen_togglePasswordVisibility() {
        composeTestRule.setContent {
            RegisterScreen(authViewModel = AuthViewModel())
        }

        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithContentDescription("Show Password").performClick()
    }

    @Test
    fun registerScreen_toggleConfirmPasswordVisibility() {
        composeTestRule.setContent {
            RegisterScreen(authViewModel = AuthViewModel())
        }

        composeTestRule.onNodeWithText("Confirm Password").performTextInput("password123")
        composeTestRule.onNodeWithContentDescription("Show Confirm Password Column").performClick()
    }
}