package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.remote.dto.garantia.GarantiaDto

class GarantiaRepository {

    private val api = ApiService.garantiaApi

    suspend fun getGarantia(servicioId: Long): Result<GarantiaDto> =
        runCatching { api.getGarantiaPorServicio(servicioId) }
}