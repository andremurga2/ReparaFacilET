package com.prueba2.reparafacil.ui.screens.tecnico

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto

@Composable
fun DetalleReparacionScreen(
    servicio: ServicioDto,
    onFinalizar: () -> Unit
) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(servicio.titulo, style = MaterialTheme.typography.headlineMedium)
        Text(servicio.descripcion)

        Text(
            "Estado: ${servicio.estado.uppercase()}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onFinalizar,
            modifier = Modifier.fillMaxWidth(),
            enabled = servicio.estado.lowercase() != "completado"
        ) {
            Text("Marcar como completado")
        }
    }
}
