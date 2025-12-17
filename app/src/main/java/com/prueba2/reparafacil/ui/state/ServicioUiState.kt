package com.prueba2.reparafacil.ui.state

import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto

data class ServicioUiState(
    val servicios: List<ServicioDto> = emptyList(),
    val servicioSeleccionado: ServicioDto? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)