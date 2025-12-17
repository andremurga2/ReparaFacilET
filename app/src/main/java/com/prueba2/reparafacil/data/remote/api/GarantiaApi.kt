package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.garantia.GarantiaDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GarantiaApi {

    @GET("garantias/servicio/{servicioId}")
    suspend fun getGarantiaPorServicio(@Path("servicioId") servicioId: Long): GarantiaDto
}
