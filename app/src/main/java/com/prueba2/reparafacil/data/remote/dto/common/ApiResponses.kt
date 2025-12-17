package com.prueba2.reparafacil.data.remote.dto.common

data class ApiItemResponse<T>(
    val success: Boolean,
    val message: String? = null,
    val data: T
)

data class ApiListResponse<T>(
    val success: Boolean,
    val data: List<T>,
    val total: Int? = null
)

data class ApiMessageResponse(
    val success: Boolean,
    val message: String
)
