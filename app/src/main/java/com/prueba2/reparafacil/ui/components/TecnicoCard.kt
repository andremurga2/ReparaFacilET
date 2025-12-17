package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.tecnico.TecnicoDto

@Composable
fun TecnicoCard(
    tecnico: TecnicoDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(tecnico.nombre, style = MaterialTheme.typography.titleMedium)
            Text(tecnico.especialidad ?: "Sin especialidad", style = MaterialTheme.typography.bodyMedium)

            val rating = tecnico.rating // ✅ alias
            if (rating != null) {
                Text("Calificación: $rating", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
