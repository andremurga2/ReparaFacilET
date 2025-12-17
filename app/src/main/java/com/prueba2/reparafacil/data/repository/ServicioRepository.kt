package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.remote.api.UpdateEstadoRequest
import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.remote.dto.tracking.TrackingEventDto

class ServicioRepository {

    private val api = ApiService.servicioApi

    suspend fun crearServicio(req: CrearServicioRequest): Result<ServicioDto> =
        runCatching { api.crearServicio(req) }

    suspend fun getServiciosCliente(): Result<List<ServicioDto>> =
        runCatching { api.getServiciosCliente() }

    suspend fun getServiciosTecnico(): Result<List<ServicioDto>> =
        runCatching { api.getServiciosTecnico() }

    suspend fun getServicio(id: String): Result<ServicioDto> =
        runCatching { api.getServicio(id) }

    suspend fun updateEstado(id: String, estado: String): Result<ServicioDto> =
        runCatching { api.updateEstado(id, UpdateEstadoRequest(estado)) }

    suspend fun getTracking(id: String): Result<List<TrackingEventDto>> =
        runCatching { api.getTracking(id) }
}
