package com.prueba2.reparafacil.ui.state

import com.prueba2.reparafacil.data.remote.dto.garantia.GarantiaDto

data class GarantiaUiState(
    val garantia: GarantiaDto? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)