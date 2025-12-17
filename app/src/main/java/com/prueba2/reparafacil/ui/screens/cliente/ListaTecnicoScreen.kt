package com.prueba2.reparafacil.ui.screens.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.tecnico.TecnicoDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTecnicosScreen(
    tecnicos: List<TecnicoDto>,
    loading: Boolean,
    errorMessage: String?,
    onRefresh: () -> Unit,
    onSelect: (TecnicoDto) -> Unit,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Técnicos") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Volver") } }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Button(
                onClick = onRefresh,
                enabled = !loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (loading) "Cargando..." else "Actualizar")
            }

            Spacer(Modifier.height(12.dp))

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

            if (tecnicos.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay técnicos disponibles.")
                }
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tecnicos) { tecnico ->
                    ListItem(
                        headlineContent = { Text(tecnico.nombre) },
                        supportingContent = { Text(tecnico.especialidad ?: "Sin especialidad") },
                        trailingContent = {
                            Button(onClick = { onSelect(tecnico) }) { Text("Elegir") }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
