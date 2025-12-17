package com.prueba2.reparafacil.data.remote.dto.tecnico

data class CreateTecnicoRequest(
    val nombre: String,
    val descripcion: String? = null,
    val email: String,
    val especialidad: String? = null,
    val telefono: String? = null,
    val calificacion: Double? = null
)

data class UpdateTecnicoRequest(
    val nombre: String? = null,
    val descripcion: String? = null,
    val email: String? = null,
    val especialidad: String? = null,
    val telefono: String? = null,
    val calificacion: Double? = null,
    val imagen: String? = null,
    val imagenThumbnail: String? = null
)
