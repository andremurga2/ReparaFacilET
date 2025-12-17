package com.prueba2.reparafacil

import com.prueba2.reparafacil.ui.state.LoginUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginUiStateTest {

    @Test
    fun cambioEmail_actualizaValor() {
        val state = LoginUiState()

        val nuevo = state.copy(email = "nuevo@mail.com")

        assertEquals("nuevo@mail.com", nuevo.email)
    }
}