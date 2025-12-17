package com.prueba2.reparafacil.ui.screens.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(
    citas: List<AgendaDto>,
    loading: Boolean = false,
    errorMessage: String? = null,
    onRefresh: () -> Unit,
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Agenda") },
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

            Button(
                onClick = onRefresh,
                modifier = Modifier.fillMaxWidth(),
                enabled = !loading
            ) {
                Text(if (loading) "Cargando..." else "Actualizar")
            }

            Spacer(modifier = Modifier.height(16.dp))

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

            if (citas.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Aún no tienes citas agendadas.")
                }
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(citas) { cita ->
                    ListItem(
                        headlineContent = { Text(cita.direccion) },
                        supportingContent = { Text(cita.fechaHora) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
