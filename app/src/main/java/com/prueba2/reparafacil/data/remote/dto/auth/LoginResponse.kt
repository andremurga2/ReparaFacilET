package com.prueba2.reparafacil.data.remote.dto.auth

import com.prueba2.reparafacil.data.remote.dto.user.UserDto

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val user: UserDto,
    val access_token: String
)
