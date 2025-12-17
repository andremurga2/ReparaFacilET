package com.prueba2.reparafacil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.tracking.TrackingEventDto
import com.prueba2.reparafacil.data.repository.ServicioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SeguimientoUiState(
    val loading: Boolean = false,
    val events: List<TrackingEventDto> = emptyList(),
    val error: String? = null
)

class SeguimientoServicioViewModel(
    private val repo: ServicioRepository = ServicioRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(SeguimientoUiState())
    val state: StateFlow<SeguimientoUiState> = _state

    fun load(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.getTracking(id)
                .onSuccess { events ->
                    _state.value = SeguimientoUiState(events = events, loading = false)
                }
                .onFailure { e ->
                    _state.value = SeguimientoUiState(
                        error = e.message ?: "Error cargando seguimiento",
                        loading = false
                    )
                }
        }
    }
}
