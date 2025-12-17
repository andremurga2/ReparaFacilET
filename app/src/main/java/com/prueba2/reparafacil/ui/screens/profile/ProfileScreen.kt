package com.prueba2.reparafacil.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.user.UserDto

@Composable
fun ProfileScreen(
    user: UserDto?,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        if (user != null) {
            // ✅ No hay 'nombre' en UserDto (backend), usamos email como identificador
            Text("Usuario: ${user.email}")
            Text("Correo: ${user.email}")
            Text("Rol: ${user.rol}")
        } else {
            Text("No hay usuario cargado", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(24.dp))

        Button(onClick = onLogout) {
            Text("Cerrar sesión")
        }
    }
}
