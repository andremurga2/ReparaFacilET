package com.prueba2.reparafacil.data.remote.api

import com.prueba2.reparafacil.data.remote.dto.tecnico.*
import com.prueba2.reparafacil.data.remote.dto.common.ApiItemResponse
import com.prueba2.reparafacil.data.remote.dto.common.ApiListResponse
import com.prueba2.reparafacil.data.remote.dto.common.ApiMessageResponse

import okhttp3.MultipartBody
import retrofit2.http.*

interface TecnicoApi {

    @GET("tecnico")
    suspend fun findAll(): ApiListResponse<TecnicoDto>

    @GET("tecnico/{id}")
    suspend fun findOne(@Path("id") id: String): ApiItemResponse<TecnicoDto>

    @POST("tecnico")
    suspend fun create(@Body body: CreateTecnicoRequest): ApiItemResponse<TecnicoDto>

    @PATCH("tecnico/{id}")
    suspend fun update(
        @Path("id") id: String,
        @Body body: UpdateTecnicoRequest
    ): ApiItemResponse<TecnicoDto>

    @DELETE("tecnico/{id}")
    suspend fun remove(@Path("id") id: String): ApiMessageResponse

    // ✅ Swagger: POST /api/tecnico/{id}/upload-image (multipart/form-data)
    @Multipart
    @POST("tecnico/{id}/upload-image")
    suspend fun uploadImage(
        @Path("id") id: String,
        @Part file: MultipartBody.Part
    ): ApiItemResponse<UploadImageResponse>
}
