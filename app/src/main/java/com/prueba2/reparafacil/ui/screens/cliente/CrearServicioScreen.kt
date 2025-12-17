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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearServicioScreen(
    loading: Boolean = false,
    errorMessage: String? = null,
    onSubmit: (CrearServicioRequest) -> Unit,
    onBack: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    // Obligatoria según tu DTO
    var fecha by remember { mutableStateOf("") } // Formato sugerido: YYYY-MM-DD

    // Opcional: si después eliges técnico desde "ListaTecnicos"
    var tecnicoId by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }

    fun validate(): Boolean {
        if (titulo.trim().isEmpty()) { error = "Título es obligatorio"; return false }
        if (descripcion.trim().isEmpty()) { error = "Descripción es obligatoria"; return false }
        if (direccion.trim().isEmpty()) { error = "Dirección es obligatoria"; return false }
        if (fecha.trim().isEmpty()) { error = "Fecha es obligatoria"; return false }
        error = null
        return true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nuevo Servicio") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Volver") } }
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
                value = titulo,
                onValueChange = { titulo = it; error = null },
                label = { Text("Título") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it; error = null },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it; error = null },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = fecha,
                onValueChange = { fecha = it; error = null },
                label = { Text("Fecha deseada (YYYY-MM-DD)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Opcional: déjalo si quieres poder asignar técnico manualmente por ahora.
            OutlinedTextField(
                value = tecnicoId,
                onValueChange = { tecnicoId = it; error = null },
                label = { Text("ID Técnico (opcional)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            val showError = error ?: errorMessage
            if (showError != null) {
                Text(showError, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    if (!validate()) return@Button

                    onSubmit(
                        CrearServicioRequest(
                            titulo = titulo.trim(),
                            descripcion = descripcion.trim(),
                            direccion = direccion.trim(),
                            tecnico = tecnicoId.trim().ifBlank { null },
                            fecha = fecha.trim()
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !loading
            ) {
                Text(if (loading) "Enviando..." else "Crear Servicio")
            }
        }
    }
}
