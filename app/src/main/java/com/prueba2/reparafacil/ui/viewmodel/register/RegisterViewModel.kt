package com.prueba2.reparafacil.ui.viewmodel.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.auth.RegisterRequest
import com.prueba2.reparafacil.data.repository.UserRepository
import com.prueba2.reparafacil.ui.state.RegisterUiState
import com.prueba2.reparafacil.ui.validation.Validators
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterUiState())
        private set

    fun onNombreChange(value: String) {
        val err = Validators.required(value, "Nombre")
        state = state.copy(nombre = value, nombreError = err, errorMessage = null)
    }

    fun onEmailChange(value: String) {
        val err = Validators.email(value)
        state = state.copy(email = value, emailError = err, errorMessage = null)
    }

    fun onPasswordChange(value: String) {
        val err = Validators.password(value)
        state = state.copy(password = value, passwordError = err, errorMessage = null)
    }

    fun onRolChange(value: String) {
        state = state.copy(rol = value, errorMessage = null)
    }

    fun onCertificacionInputChange(value: String) {
        state = state.copy(certificacionInput = value)
    }

    fun addCertificacion() {
        val item = state.certificacionInput.trim()
        if (item.isEmpty()) return
        if (state.certificaciones.any { it.equals(item, ignoreCase = true) }) {
            state = state.copy(certificacionInput = "")
            return
        }
        state = state.copy(
            certificaciones = state.certificaciones + item,
            certificacionInput = ""
        )
    }

    fun removeCertificacion(value: String) {
        state = state.copy(certificaciones = state.certificaciones.filterNot { it == value })
    }

    fun register(onSuccess: (String) -> Unit) {
        val nombreErr = Validators.required(state.nombre, "Nombre")
        val emailErr = Validators.email(state.email)
        val passErr = Validators.password(state.password)

        state = state.copy(
            nombreError = nombreErr,
            emailError = emailErr,
            passwordError = passErr,
            errorMessage = null
        )

        if (nombreErr != null || emailErr != null || passErr != null) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val response = userRepository.register(
                    RegisterRequest(
                        nombre = state.nombre.trim(),
                        email = state.email.trim(),
                        password = state.password,
                        rol = state.rol,
                        certificaciones = if (state.rol == "TECNICO") state.certificaciones else emptyList()
                    )
                )

                val rol = response.data.user.rol.uppercase()
                state = state.copy(isLoading = false)
                onSuccess(rol)

            } catch (e: HttpException) {
                state = state.copy(isLoading = false, errorMessage = "HTTP ${e.code()}")
            } catch (e: Exception) {
                state = state.copy(isLoading = false, errorMessage = e.message ?: "Error al registrar")
            }
        }
    }
}
