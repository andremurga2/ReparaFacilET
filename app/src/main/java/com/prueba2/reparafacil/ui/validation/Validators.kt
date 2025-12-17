package com.prueba2.reparafacil.ui.validation

import android.util.Patterns

object Validators {

    fun email(value: String): String? {
        val v = value.trim()
        if (v.isBlank()) return "Ingresa tu correo"
        if (!Patterns.EMAIL_ADDRESS.matcher(v).matches()) return "Correo inválido"
        return null
    }

    fun password(value: String): String? {
        if (value.isBlank()) return "Ingresa tu contraseña"
        if (value.length < 6) return "Mínimo 6 caracteres"
        return null
    }

    fun required(value: String, field: String): String? {
        return if (value.trim().isEmpty()) "$field es obligatorio" else null
    }
}
