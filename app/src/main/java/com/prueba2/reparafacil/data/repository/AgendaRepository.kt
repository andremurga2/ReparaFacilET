package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.remote.dto.agenda.AgendaDto

class AgendaRepository {

    private val api = ApiService.agendaApi

    suspend fun getAgendaCliente(): Result<List<AgendaDto>> =
        runCatching { api.getAgendaCliente() }

    suspend fun getAgendaTecnico(): Result<List<AgendaDto>> =
        runCatching { api.getAgendaTecnico() }

    suspend fun crearCita(dto: AgendaDto): Result<AgendaDto> =
        runCatching { api.crearCita(dto) }
}