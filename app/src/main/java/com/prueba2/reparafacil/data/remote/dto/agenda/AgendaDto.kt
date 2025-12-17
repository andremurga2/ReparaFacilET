package com.prueba2.reparafacil.data.remote.dto.agenda

import com.google.gson.annotations.SerializedName

data class AgendaDto(
    @SerializedName("_id")
    val id: String,
    val servicioId: String,
    val fechaHora: String,
    val direccion: String,
    val estado: String
)
