package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.remote.dto.notificacion.NotificacionDto

class NotificacionesRepository {
    private val api = ApiService.notificacionesApi

    suspend fun getNotificaciones(): Result<List<NotificacionDto>> =
        runCatching { api.getNotificaciones() }

    suspend fun marcarLeida(id: String): Result<NotificacionDto> =
        runCatching { api.marcarLeida(id) }
}
