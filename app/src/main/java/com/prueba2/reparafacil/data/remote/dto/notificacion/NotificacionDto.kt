package com.prueba2.reparafacil.data.remote.dto.notificacion

data class NotificacionDto(
    val id: String,
    val userId: String,
    val type: String,
    val title: String,
    val message: String,
    val read: Boolean,
    val createdAt: String
)
