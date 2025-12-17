package com.prueba2.reparafacil.ui.viewmodel.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeTecnicoViewModel(
    private val repo: ServicioRepository
) : ViewModel() {

    private val _asignados = MutableStateFlow<List<ServicioDto>>(emptyList())
    val asignados: StateFlow<List<ServicioDto>> = _asignados

    fun cargarAsignados() {
        viewModelScope.launch {
            repo.getServiciosTecnico()
                .onSuccess { _asignados.value = it }
                .onFailure { _asignados.value = emptyList() }
        }
    }
}