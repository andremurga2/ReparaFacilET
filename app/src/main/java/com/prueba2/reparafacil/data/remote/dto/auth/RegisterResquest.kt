package com.prueba2.reparafacil.data.remote.dto.auth

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String, // CLIENTE o TECNICO
    val certificaciones: List<String> = emptyList()
)
