package com.prueba2.reparafacil.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("_id")
    val id: String,

    val email: String,

    @SerializedName("role")
    val rol: String,

    val isActive: Boolean? = null,
    val emailVerified: Boolean? = null,

    // si tu backend lo devuelve a veces
    val certificaciones: List<String>? = null
)
