package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto

@Composable
fun ServicioCard(
    servicio: ServicioDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(servicio.titulo, style = MaterialTheme.typography.titleMedium)
            Text(servicio.estado, style = MaterialTheme.typography.bodyMedium)
            Text(servicio.direccion, style = MaterialTheme.typography.bodySmall)
        }
    }
}