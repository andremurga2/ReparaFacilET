package com.prueba2.reparafacil.data.remote.dto.auth

import com.prueba2.reparafacil.data.remote.dto.user.UserDto

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val data: RegisterData
)

data class RegisterData(
    val user: UserDto,
    val access_token: String
)
