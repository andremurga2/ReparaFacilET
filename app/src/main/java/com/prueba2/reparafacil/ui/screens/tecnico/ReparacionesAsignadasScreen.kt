package com.prueba2.reparafacil.ui.screens.tecnico

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto

@Composable
fun ReparacionesAsignadasScreen(
    servicios: List<ServicioDto>,
    onSelect: (ServicioDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Reparaciones Asignadas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(servicios) { serv ->
                ListItem(
                    headlineContent = { Text(serv.titulo) },
                    supportingContent = { Text(serv.estado) },
                    trailingContent = {
                        Button(onClick = { onSelect(serv) }) {
                            Text("Ver")
                        }
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

