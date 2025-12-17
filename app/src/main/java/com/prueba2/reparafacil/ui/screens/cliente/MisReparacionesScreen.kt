package com.prueba2.reparafacil.ui.screens.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisReparacionesScreen(
    servicios: List<ServicioDto>,
    loading: Boolean = false,
    errorMessage: String? = null,
    onSelect: (ServicioDto) -> Unit,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reparaciones") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Volver") } }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            if (errorMessage != null) {
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
            }

            if (loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                return@Column
            }

            if (servicios.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Aún no tienes reparaciones.")
                }
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(servicios) { servicio ->
                    ListItem(
                        headlineContent = { Text(servicio.titulo) },
                        supportingContent = { Text(servicio.estado) },
                        trailingContent = { Button(onClick = { onSelect(servicio) }) { Text("Ver") } }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
