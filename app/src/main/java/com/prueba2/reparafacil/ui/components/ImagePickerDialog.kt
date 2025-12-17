package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImagePickerDialog(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {}, // no usamos botón de confirmación
        title = {
            Text(text = "Seleccionar imagen")
        },
        text = {
            Column(Modifier.padding(top = 8.dp)) {

                TextButton(onClick = {
                    onCameraClick()
                    onDismiss()
                }) {
                    Text("Tomar foto")
                }

                TextButton(onClick = {
                    onGalleryClick()
                    onDismiss()
                }) {
                    Text("Elegir desde galería")
                }
            }
        }
    )
}