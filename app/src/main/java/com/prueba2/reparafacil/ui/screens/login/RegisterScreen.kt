package com.prueba2.reparafacil.ui.screens.login


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.ui.state.RegisterUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onNombreChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRolChange: (String) -> Unit,
    onCertificacionInputChange: (String) -> Unit,
    onAddCertificacion: () -> Unit,
    onRemoveCertificacion: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←") }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Error general (backend / http)
            state.errorMessage?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            OutlinedTextField(
                value = state.nombre,
                onValueChange = onNombreChange,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nombreError != null,
                supportingText = { state.nombreError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = onEmailChange,
                label = { Text("Correo") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.emailError != null,
                supportingText = { state.emailError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.password,
                onValueChange = onPasswordChange,
                label = { Text("Contraseña") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = state.passwordError != null,
                supportingText = { state.passwordError?.let { Text(it) } }
            )

            Text("Tipo de usuario", style = MaterialTheme.typography.titleSmall)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                FilterChip(
                    selected = state.rol == "CLIENTE",
                    onClick = { onRolChange("CLIENTE") },
                    label = { Text("CLIENTE") }
                )
                FilterChip(
                    selected = state.rol == "TECNICO",
                    onClick = { onRolChange("TECNICO") },
                    label = { Text("TECNICO") }
                )
            }

            // Certificaciones solo si es técnico
            if (state.rol == "TECNICO") {
                Divider()

                Text("Certificaciones (obligatorio que sea array)", style = MaterialTheme.typography.titleSmall)

                OutlinedTextField(
                    value = state.certificacionInput,
                    onValueChange = onCertificacionInputChange,
                    label = { Text("Ej: Electricidad") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = onAddCertificacion,
                        enabled = !state.isLoading
                    ) {
                        Text("Agregar")
                    }

                    Text(
                        text = "Tip: toca una para quitarla",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                if (state.certificaciones.isNotEmpty()) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(state.certificaciones) { c ->
                            AssistChip(
                                onClick = { onRemoveCertificacion(c) },
                                label = { Text(c) }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onRegisterClick,
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text("Crear cuenta")
            }

            OutlinedButton(
                onClick = onBack,
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}
