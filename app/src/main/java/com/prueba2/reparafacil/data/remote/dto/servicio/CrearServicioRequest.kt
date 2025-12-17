package com.prueba2.reparafacil.data.remote.dto.servicio

data class CrearServicioRequest(
    val titulo: String,
    val descripcion: String,
    val direccion: String,
    val tecnico: String? = null,
    val fecha: String
)
