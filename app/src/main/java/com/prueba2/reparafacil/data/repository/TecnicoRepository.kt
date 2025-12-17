package com.prueba2.reparafacil.data.repository

import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.remote.dto.tecnico.CreateTecnicoRequest
import com.prueba2.reparafacil.data.remote.dto.tecnico.TecnicoDto
import com.prueba2.reparafacil.data.remote.dto.tecnico.UpdateTecnicoRequest
import com.prueba2.reparafacil.data.remote.dto.tecnico.UploadImageResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class TecnicoRepository {

    private val api = ApiService.tecnicoApi

    suspend fun getTecnicos(): Result<List<TecnicoDto>> = runCatching {
        api.findAll().data
    }

    suspend fun getTecnico(id: String): Result<TecnicoDto> = runCatching {
        api.findOne(id).data
    }

    suspend fun crearTecnico(req: CreateTecnicoRequest): Result<TecnicoDto> = runCatching {
        api.create(req).data
    }

    suspend fun actualizarTecnico(id: String, req: UpdateTecnicoRequest): Result<TecnicoDto> = runCatching {
        api.update(id, req).data
    }

    suspend fun eliminarTecnico(id: String): Result<String> = runCatching {
        api.remove(id).message
    }

    // ✅ subir imagen (File -> Multipart)
    suspend fun subirImagenTecnico(id: String, file: File): Result<UploadImageResponse> = runCatching {
        val mime = "image/*".toMediaType()
        val body = file.asRequestBody(mime)
        val part = MultipartBody.Part.createFormData("file", file.name, body)

        api.uploadImage(id, part).data
    }
}
