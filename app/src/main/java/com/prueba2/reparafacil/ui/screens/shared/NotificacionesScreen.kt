package com.prueba2.reparafacil.ui.screens.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prueba2.reparafacil.ui.viewmodel.NotificacionesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionesScreen(
    onBack: () -> Unit,
    vm: NotificacionesViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) { vm.load() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.Notifications, null) } }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize().padding(16.dp)) {

            AnimatedVisibility(visible = state.error != null) {
                Text(state.error ?: "", color = MaterialTheme.colorScheme.error)
            }

            if (state.loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                return@Column
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(state.items) { n ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { if (!n.read) vm.marcarLeida(n.id) }
                    ) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Notifications, null)
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(n.title, style = MaterialTheme.typography.titleMedium)
                                Text(n.message, style = MaterialTheme.typography.bodyMedium)
                                Text(n.createdAt, style = MaterialTheme.typography.labelSmall)
                            }
                            if (n.read) Icon(Icons.Default.Done, null)
                        }
                    }
                }
            }
        }
    }
}
