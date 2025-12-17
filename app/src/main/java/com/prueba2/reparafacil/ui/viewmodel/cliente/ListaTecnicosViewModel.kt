package com.prueba2.reparafacil.ui.viewmodel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.repository.TecnicoRepository
import com.prueba2.reparafacil.ui.state.TecnicoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaTecnicosViewModel(
    private val repo: TecnicoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TecnicoUiState(isLoading = false))
    val state: StateFlow<TecnicoUiState> = _state

    fun cargarTecnicos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)

            repo.getTecnicos()
                .onSuccess { list ->
                    _state.value = TecnicoUiState(
                        tecnicos = list,
                        isLoading = false,
                        errorMessage = null
                    )
                }
                .onFailure { e ->
                    val msg = e.message ?: "Error al cargar técnicos"
                    _state.value = TecnicoUiState(
                        tecnicos = emptyList(),
                        isLoading = false,
                        errorMessage = msg
                    )
                }
        }
    }
}
