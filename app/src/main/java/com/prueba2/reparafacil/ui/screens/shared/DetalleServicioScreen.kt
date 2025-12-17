package com.prueba2.reparafacil.ui.screens.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prueba2.reparafacil.ui.viewmodel.DetalleServicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleServicioScreen(
    servicioId: String,
    onBack: () -> Unit,
    onVerSeguimiento: (String) -> Unit,
    vm: DetalleServicioViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(servicioId) { vm.load(servicioId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del servicio") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            AnimatedVisibility(visible = state.error != null) {
                Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
            }

            if (state.loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                return@Column
            }

            val s = state.servicio ?: return@Column

            Text(s.titulo, style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text(s.descripcion)
            Spacer(Modifier.height(8.dp))
            Text("Estado: ${s.estado}", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { onVerSeguimiento(servicioId) },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Ver seguimiento") }

            Spacer(Modifier.height(10.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { vm.actualizarEstado(servicioId, "EN_CAMINO") }
                ) {
                    Text("En camino")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { vm.actualizarEstado(servicioId, "FINALIZADO") }
                ) {
                    Text("Finalizar")
                }
            }
        }
    }
}
