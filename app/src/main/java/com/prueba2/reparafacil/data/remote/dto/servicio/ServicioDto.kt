package com.prueba2.reparafacil.data.remote.dto.servicio

import com.google.gson.annotations.SerializedName
import com.prueba2.reparafacil.data.remote.dto.garantia.GarantiaDto
import com.prueba2.reparafacil.data.remote.dto.tecnico.TecnicoDto
import com.prueba2.reparafacil.data.remote.dto.user.UserDto

data class ServicioDto(
    @SerializedName("_id")
    val id: String,

    val titulo: String,
    val descripcion: String,
    val direccion: String,
    val estado: String,
    val fechaSolicitud: String,

    val cliente: UserDto,
    val tecnico: TecnicoDto?,
    val garantia: GarantiaDto?
)
