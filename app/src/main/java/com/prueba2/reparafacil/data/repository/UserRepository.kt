package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.local.SessionManager
import com.prueba2.reparafacil.data.remote.api.AuthApi
import com.prueba2.reparafacil.data.remote.dto.auth.LoginRequest
import com.prueba2.reparafacil.data.remote.dto.auth.LoginResponse
import com.prueba2.reparafacil.data.remote.dto.auth.RegisterRequest
import com.prueba2.reparafacil.data.remote.dto.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val api: AuthApi,
    private val sessionManager: SessionManager
) {

    suspend fun login(request: LoginRequest): LoginResponse {
        val response = api.login(request)

        sessionManager.saveToken(response.data.access_token)
        sessionManager.saveUserId(response.data.user.id)
        sessionManager.saveUserRole(response.data.user.rol)

        return response
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        val response = api.register(request)

        // ✅ guardar sesión también al registrar
        sessionManager.saveToken(response.data.access_token)
        sessionManager.saveUserId(response.data.user.id)
        sessionManager.saveUserRole(response.data.user.rol)

        return response
    }

    suspend fun logout() {
        sessionManager.clearSession()
    }

    val tokenFlow: Flow<String?> = sessionManager.token
    val isLoggedInFlow: Flow<Boolean> = sessionManager.token.map { it != null }
    val userRoleFlow: Flow<String?> = sessionManager.userRole

    suspend fun isLoggedIn(): Boolean =
        sessionManager.getToken() != null

    suspend fun getUserRole(): String? =
        sessionManager.getUserRole()
}
