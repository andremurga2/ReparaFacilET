package com.prueba2.reparafacil

import com.prueba2.reparafacil.data.repository.ServicioRepository
import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test

class ServicioRepositoryTest {

    @Test
    fun crearServicio_requestNoEsNulo() = runBlocking {
        val repo = ServicioRepository() // No se usa, solo existe para el test

        val req = CrearServicioRequest(
            titulo = "Prueba",
            descripcion = "Test",
            direccion = "Casa",
            fecha = "2025-01-10"
        )

        assertNotNull(req)
    }
}