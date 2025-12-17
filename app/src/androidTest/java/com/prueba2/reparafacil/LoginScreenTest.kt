package com.prueba2.reparafacil


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.prueba2.reparafacil.ui.screens.login.LoginScreen
import com.prueba2.reparafacil.ui.state.LoginUiState
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun loginScreen_muestraBotonCliente() {
        composeRule.setContent {
            LoginScreen(
                state = LoginUiState(),
                onEmailChange = {},
                onPasswordChange = {},
                onLoginClick = {},
                onRegisterClick = {}
            )
        }

        composeRule
            .onNodeWithText("Entrar como Cliente")
            .assertExists()
    }
}
