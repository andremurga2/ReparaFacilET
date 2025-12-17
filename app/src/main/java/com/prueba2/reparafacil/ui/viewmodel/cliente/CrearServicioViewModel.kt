package com.prueba2.reparafacil.ui.viewmodel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CrearServicioViewModel(
    private val repo: ServicioRepository
) : ViewModel() {

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    fun crear(request: CrearServicioRequest) {
        viewModelScope.launch {
            repo.crearServicio(request)
                .onSuccess { _success.value = true }
                .onFailure { _success.value = false }
        }
    }
}
