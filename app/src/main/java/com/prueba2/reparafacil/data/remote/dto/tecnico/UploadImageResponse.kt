package com.prueba2.reparafacil.data.remote.dto.tecnico


data class UploadImageResponse(
    val tecnico: TecnicoDto,
    val upload: UploadResultDto
)

data class UploadResultDto(
    val url: String,
    val thumbnailUrl: String
)
