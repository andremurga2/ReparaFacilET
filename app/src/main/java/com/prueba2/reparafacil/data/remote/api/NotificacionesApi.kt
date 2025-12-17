package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.notificacion.NotificacionDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NotificacionesApi {

    @GET("notifications")
    suspend fun getNotificaciones(): List<NotificacionDto>

    @PATCH("notifications/{id}/read")
    suspend fun marcarLeida(@Path("id") id: String): NotificacionDto
}
