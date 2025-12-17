package com.prueba2.reparafacil.ui.viewmodel.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReparacionesAsignadasViewModel(
    private val repo: ServicioRepository
) : ViewModel() {

    private val _reparaciones = MutableStateFlow<List<ServicioDto>>(emptyList())
    val reparaciones: StateFlow<List<ServicioDto>> = _reparaciones

    fun cargar() {
        viewModelScope.launch {
            repo.getServiciosTecnico()
                .onSuccess { _reparaciones.value = it }
                .onFailure { _reparaciones.value = emptyList() }
        }
    }
}