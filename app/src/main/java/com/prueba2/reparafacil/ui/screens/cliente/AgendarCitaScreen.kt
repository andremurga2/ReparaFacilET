package com.prueba2.reparafacil.ui.screens.cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(
    servicioId: String,
    loading: Boolean = false,
    errorMessage: String? = null,
    onSubmit: (AgendaDto) -> Unit,
    onBack: () -> Unit = {}
) {
    var fechaHora by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        if (fechaHora.trim().isEmpty()) {
            localError = "Ingresa fecha y hora."
            return false
        }
        if (direccion.trim().isEmpty()) {
            localError = "Ingresa dirección."
            return false
        }
        localError = null
        return true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar Cita") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = fechaHora,
                onValueChange = {
                    fechaHora = it
                    localError = null
                },
                label = { Text("Fecha y hora") },
                singleLine = true
            )

            OutlinedTextField(
                value = direccion,
                onValueChange = {
                    direccion = it
                    localError = null
                },
                label = { Text("Dirección") },
                singleLine = true
            )

            val showError = localError ?: errorMessage
            if (showError != null) {
                Text(
                    text = showError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = !loading && fechaHora.isNotBlank() && direccion.isNotBlank(),
                onClick = {
                    if (!validate()) return@Button

                    val dto = AgendaDto(
                        id = "0", // el backend genera el real
                        servicioId = servicioId,
                        fechaHora = fechaHora.trim(),
                        direccion = direccion.trim(),
                        estado = "PENDIENTE"
                    )
                    onSubmit(dto)
                }
            ) {
                Text(if (loading) "Enviando..." else "Confirmar")
            }
        }
    }
}
