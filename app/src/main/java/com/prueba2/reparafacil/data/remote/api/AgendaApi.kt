package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AgendaApi {

    // Citas de un cliente
    @GET("agenda/cliente")
    suspend fun getAgendaCliente(): List<AgendaDto>

    // Citas de un técnico
    @GET("agenda/tecnico")
    suspend fun getAgendaTecnico(): List<AgendaDto>

    // Crear una cita nueva
    @POST("agenda")
    suspend fun crearCita(@Body cita: AgendaDto): AgendaDto
}