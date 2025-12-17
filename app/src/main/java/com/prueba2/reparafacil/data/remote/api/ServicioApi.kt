package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.remote.dto.tracking.TrackingEventDto
import retrofit2.http.*

interface ServicioApi {

    @GET("servicios/cliente")
    suspend fun getServiciosCliente(): List<ServicioDto>

    @GET("servicios/tecnico")
    suspend fun getServiciosTecnico(): List<ServicioDto>

    @POST("servicios")
    suspend fun crearServicio(
        @Body request: CrearServicioRequest
    ): ServicioDto

    @GET("servicios/{id}")
    suspend fun getServicio(
        @Path("id") id: String
    ): ServicioDto

    @PATCH("servicios/{id}/estado")
    suspend fun updateEstado(
        @Path("id") id: String,
        @Body req: UpdateEstadoRequest
    ): ServicioDto

    @GET("servicios/{id}/tracking")
    suspend fun getTracking(
        @Path("id") id: String
    ): List<TrackingEventDto>
}

data class UpdateEstadoRequest(
    val estado: String
)
