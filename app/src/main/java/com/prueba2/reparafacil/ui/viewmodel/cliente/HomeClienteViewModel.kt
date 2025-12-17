package com.prueba2.reparafacil.ui.viewmodel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeClienteViewModel(
    private val repo: ServicioRepository
) : ViewModel() {

    private val _servicios = MutableStateFlow<List<ServicioDto>>(emptyList())
    val servicios: StateFlow<List<ServicioDto>> = _servicios

    fun cargarServicios() {
        viewModelScope.launch {
            repo.getServiciosCliente()
                .onSuccess { _servicios.value = it }
                .onFailure { _servicios.value = emptyList() }
        }
    }
}