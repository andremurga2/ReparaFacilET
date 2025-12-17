package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.user.UserDto

@Composable
fun ProfileCard(user: UserDto) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // 👤 No hay nombre en el backend → mostramos email como identificador
            Text(
                text = "Usuario: ${user.email}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Correo: ${user.email}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Rol: ${user.rol}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
