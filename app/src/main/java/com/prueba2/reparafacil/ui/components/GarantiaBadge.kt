package com.prueba2.reparafacil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.prueba2.reparafacil.data.remote.dto.garantia.GarantiaDto

@Composable
fun GarantiaBadge(garantia: GarantiaDto?) {
    if (garantia == null) return

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Garantía hasta ${garantia.fechaFin}",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}