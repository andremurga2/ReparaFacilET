package com.prueba2.reparafacil.ui.di

import android.content.Context
import com.prueba2.reparafacil.data.local.SessionManager
import com.prueba2.reparafacil.data.remote.api.ApiService
import com.prueba2.reparafacil.data.repository.TecnicoRepository
import com.prueba2.reparafacil.data.repository.UserRepository

object AppDependencies {

    @Volatile private var initialized = false

    private fun ensureInit(context: Context) {
        if (!initialized) {
            ApiService.build(context.applicationContext)
            initialized = true
        }
    }

    fun provideUserRepository(context: Context): UserRepository {
        ensureInit(context)
        val session = SessionManager(context.applicationContext)
        return UserRepository(ApiService.authApi, session)
    }

    fun provideTecnicoRepository(context: Context): TecnicoRepository {
        ensureInit(context)
        return TecnicoRepository()
    }
}
