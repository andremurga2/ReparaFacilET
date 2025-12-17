package com.prueba2.reparafacil.ui.viewmodel.tecnico

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalleReparacionViewModel(
    private val repo: ServicioRepository
) : ViewModel() {

    private val _servicio = MutableStateFlow<ServicioDto?>(null)
    val servicio: StateFlow<ServicioDto?> = _servicio

    fun cargar(id: String) {
        viewModelScope.launch {
            repo.getServicio(id)
                .onSuccess { _servicio.value = it }
                .onFailure { _servicio.value = null }
        }
    }
}
