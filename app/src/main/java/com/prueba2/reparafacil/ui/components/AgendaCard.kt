package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto

@Composable
fun AgendaCard(
    agenda: AgendaDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(agenda.direccion, style = MaterialTheme.typography.titleMedium)
            Text(agenda.fechaHora, style = MaterialTheme.typography.bodyMedium)
            Text(agenda.estado, style = MaterialTheme.typography.bodySmall)
        }
    }
}