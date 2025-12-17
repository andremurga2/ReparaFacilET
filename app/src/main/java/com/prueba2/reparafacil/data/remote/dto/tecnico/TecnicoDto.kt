package com.prueba2.reparafacil.data.remote.dto.tecnico

import com.google.gson.annotations.SerializedName

data class TecnicoDto(
    @SerializedName("_id")
    val id: String,

    val nombre: String,
    val especialidad: String? = null,
    val telefono: String? = null,
    val email: String? = null,

    @SerializedName("calificacion")
    val calificacion: Double? = null,

    val imagen: String? = null,
    val imagenThumbnail: String? = null
) {
    // ✅ alias para tu UI actual (TecnicoCard)
    val rating: Double?
        get() = calificacion
}
