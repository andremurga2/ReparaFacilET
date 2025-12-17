package com.prueba2.reparafacil.ui.state

import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto

data class AgendaUiState(
    val citas: List<AgendaDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)