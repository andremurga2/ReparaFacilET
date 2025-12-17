package com.prueba2.reparafacil.ui.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    val emailError: String? = null,
    val passwordError: String? = null,

    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loggedIn: Boolean = false
) {
    val canSubmit: Boolean
        get() = emailError == null && passwordError == null &&
                email.isNotBlank() && password.isNotBlank() && !isLoading
}
