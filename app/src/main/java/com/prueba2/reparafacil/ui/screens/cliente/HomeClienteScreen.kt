package com.prueba2.reparafacil.ui.screens.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeClienteScreen(
    onCrearServicio: () -> Unit,
    onVerTecnicos: () -> Unit,
    onMisReparaciones: () -> Unit,
    onNotificaciones: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Bienvenido Cliente", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onCrearServicio, modifier = Modifier.fillMaxWidth()) { Text("Crear Nuevo Servicio") }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onVerTecnicos, modifier = Modifier.fillMaxWidth()) { Text("Ver Técnicos Disponibles") }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onMisReparaciones, modifier = Modifier.fillMaxWidth()) { Text("Mis Reparaciones") }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onNotificaciones, modifier = Modifier.fillMaxWidth()) { Text("Notificaciones") }
    }
}
