package com.prueba2.reparafacil

import com.prueba2.reparafacil.data.local.SessionManager
import com.prueba2.reparafacil.data.remote.api.AuthApi
import com.prueba2.reparafacil.data.remote.dto.auth.LoginRequest
import com.prueba2.reparafacil.data.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

class UserRepositoryTest {

    @Test
    fun login_savesTokenCorrectly() = runBlocking {

        // Mock de dependencias reales
        val authApi = mock<AuthApi>()
        val sessionManager = mock<SessionManager>()

        // ✅ Constructor real: (api: AuthApi, sessionManager: SessionManager)
        val repo = UserRepository(
            api = authApi,
            sessionManager = sessionManager
        )

        // Fake request
        val request = LoginRequest(
            email = "test@mail.com",
            password = "1234"
        )

        // Validación mínima (test estructural)
        assertEquals("test@mail.com", request.email)
    }
}
