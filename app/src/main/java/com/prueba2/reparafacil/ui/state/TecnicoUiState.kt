package com.prueba2.reparafacil.ui.state

import com.prueba2.reparafacil.data.remote.dto.tecnico.TecnicoDto

data class TecnicoUiState(
    val tecnicos: List<TecnicoDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)