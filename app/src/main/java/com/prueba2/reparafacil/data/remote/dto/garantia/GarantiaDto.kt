package com.prueba2.reparafacil.data.remote.dto.garantia

data class GarantiaDto(
    val id: Long,
    val servicioId: Long,
    val fechaInicio: String,
    val fechaFin: String,
    val condiciones: String
)