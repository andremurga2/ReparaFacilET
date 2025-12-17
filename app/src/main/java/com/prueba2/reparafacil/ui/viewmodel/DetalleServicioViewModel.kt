package com.prueba2.reparafacil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DetalleServicioUiState(
    val loading: Boolean = false,
    val servicio: ServicioDto? = null,
    val error: String? = null
)

class DetalleServicioViewModel(
    private val repo: ServicioRepository = ServicioRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(DetalleServicioUiState())
    val state: StateFlow<DetalleServicioUiState> = _state

    fun load(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.getServicio(id)
                .onSuccess { servicio ->
                    _state.value = DetalleServicioUiState(servicio = servicio, loading = false)
                }
                .onFailure { e ->
                    _state.value = DetalleServicioUiState(
                        error = e.message ?: "Error cargando servicio",
                        loading = false
                    )
                }
        }
    }

    fun actualizarEstado(id: String, estado: String) {
        viewModelScope.launch {
            repo.updateEstado(id, estado)
                .onSuccess { updated ->
                    _state.value = _state.value.copy(servicio = updated, error = null)
                }
                .onFailure { e ->
                    _state.value = _state.value.copy(
                        error = e.message ?: "Error actualizando estado"
                    )
                }
        }
    }
}
