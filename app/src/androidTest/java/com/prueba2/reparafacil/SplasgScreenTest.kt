package com.prueba2.reparafacil

import androidx.compose.ui.test.junit4.createComposeRule
import com.prueba2.reparafacil.ui.screens.shared.SplashScreen
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun splashScreen_renderizaSinErrores() {
        composeRule.setContent {
            SplashScreen()
        }
    }
}