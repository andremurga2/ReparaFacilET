package com.prueba2.reparafacil.ui.viewmodel.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.auth.LoginRequest
import com.prueba2.reparafacil.data.repository.UserRepository
import com.prueba2.reparafacil.ui.state.LoginUiState
import com.prueba2.reparafacil.ui.validation.Validators
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var state by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(value: String) {
        val err = Validators.email(value)
        state = state.copy(email = value, emailError = err, errorMessage = null)
    }

    fun onPasswordChange(value: String) {
        val err = Validators.password(value)
        state = state.copy(password = value, passwordError = err, errorMessage = null)
    }

    fun login(onSuccess: (String) -> Unit) {
        val emailErr = Validators.email(state.email)
        val passErr = Validators.password(state.password)
        state = state.copy(emailError = emailErr, passwordError = passErr, errorMessage = null)
        if (emailErr != null || passErr != null) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val response = userRepository.login(
                    LoginRequest(email = state.email.trim(), password = state.password)
                )

                val rol = response.data.user.rol.uppercase()

                state = state.copy(isLoading = false, loggedIn = true)
                onSuccess(rol)

            } catch (e: HttpException) {
                state = state.copy(
                    isLoading = false,
                    errorMessage = "HTTP ${e.code()}"
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al iniciar sesión"
                )
            }
        }
    }
}
