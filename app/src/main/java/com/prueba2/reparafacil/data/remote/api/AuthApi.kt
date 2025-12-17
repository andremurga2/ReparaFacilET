package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.auth.LoginRequest
import com.prueba2.reparafacil.data.remote.dto.auth.LoginResponse
import com.prueba2.reparafacil.data.remote.dto.auth.RegisterRequest
import com.prueba2.reparafacil.data.remote.dto.auth.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}
