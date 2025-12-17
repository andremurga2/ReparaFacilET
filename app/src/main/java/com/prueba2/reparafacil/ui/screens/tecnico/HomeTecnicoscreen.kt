package com.prueba2.reparafacil.ui.screens.tecnico

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeTecnicoScreen(
    onVerReparaciones: () -> Unit,
    onMiAgenda: () -> Unit,
    onNotificaciones: () -> Unit
) {
    Column(
        Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Panel Técnico", style = MaterialTheme.typography.headlineMedium)

        Button(
            onClick = onVerReparaciones,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reparaciones Asignadas")
        }

        Button(
            onClick = onMiAgenda,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mi Agenda")
        }

        Button(
            onClick = onNotificaciones,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Notificaciones")
        }
    }
}