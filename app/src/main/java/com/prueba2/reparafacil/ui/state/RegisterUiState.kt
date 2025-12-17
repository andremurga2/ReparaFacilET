package com.prueba2.reparafacil.ui.state

data class RegisterUiState(
    val nombre: String = "",
    val email: String = "",
    val password: String = "",
    val rol: String = "CLIENTE", // CLIENTE o TECNICO
    val certificacionInput: String = "",
    val certificaciones: List<String> = emptyList(),

    val nombreError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
