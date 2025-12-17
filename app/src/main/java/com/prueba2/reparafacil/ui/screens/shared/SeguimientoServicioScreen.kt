package com.prueba2.reparafacil.ui.screens.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prueba2.reparafacil.ui.viewmodel.SeguimientoServicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeguimientoServicioScreen(
    servicioId: String,
    onBack: () -> Unit,
    vm: SeguimientoServicioViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(servicioId) { vm.load(servicioId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguimiento") },
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

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.events) { e ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(e.status, style = MaterialTheme.typography.titleMedium)
                            Text(e.at, style = MaterialTheme.typography.labelSmall)
                            if (!e.note.isNullOrBlank()) {
                                Spacer(Modifier.height(6.dp))
                                Text(e.note ?: "")
                            }
                        }
                    }
                }
            }
        }
    }
}
