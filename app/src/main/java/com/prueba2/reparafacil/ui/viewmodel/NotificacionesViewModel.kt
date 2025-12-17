package com.prueba2.reparafacil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.notificacion.NotificacionDto
import com.prueba2.reparafacil.data.repository.NotificacionesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class NotificacionesUiState(
    val loading: Boolean = false,
    val items: List<NotificacionDto> = emptyList(),
    val error: String? = null
)

class NotificacionesViewModel(
    private val repo: NotificacionesRepository = NotificacionesRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(NotificacionesUiState())
    val state: StateFlow<NotificacionesUiState> = _state

    fun load() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            repo.getNotificaciones()
                .onSuccess { _state.value = NotificacionesUiState(items = it, loading = false) }
                .onFailure { _state.value = NotificacionesUiState(error = it.message ?: "Error", loading = false) }
        }
    }

    fun marcarLeida(id: String) {
        viewModelScope.launch {
            repo.marcarLeida(id).onSuccess { updated ->
                _state.value = _state.value.copy(items = _state.value.items.map { if (it.id == id) updated else it })
            }
        }
    }
}
