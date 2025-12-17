package com.prueba2.reparafacil.ui.viewmodel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgendarCitaViewModel : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    /**
     * Por ahora "arma" el DTO y simula éxito.
     * Luego conectamos con AgendaRepository (POST /agenda).
     */
    fun agendar(servicioId: String, fechaHora: String, direccion: String): AgendaDto? {
        val fh = fechaHora.trim()
        val dir = direccion.trim()

        if (fh.isEmpty()) {
            _errorMessage.value = "Ingresa fecha y hora."
            return null
        }
        if (dir.isEmpty()) {
            _errorMessage.value = "Ingresa dirección."
            return null
        }

        _errorMessage.value = null

        val dto = AgendaDto(
            id = "0",                 // Mongo id real lo asigna backend
            servicioId = servicioId,  // ✅ String
            fechaHora = fh,
            direccion = dir,
            estado = "PENDIENTE"
        )

        viewModelScope.launch {
            _loading.value = true
            try {
                // TODO: aquí irá repo.crearCita(dto) cuando exista AgendaRepository
                _success.value = true
            } catch (e: Exception) {
                _success.value = false
                _errorMessage.value = e.message ?: "Error al agendar."
            } finally {
                _loading.value = false
            }
        }

        return dto
    }

    fun resetSuccess() {
        _success.value = false
    }
}
