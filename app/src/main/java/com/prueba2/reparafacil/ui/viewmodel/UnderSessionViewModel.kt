package com.prueba2.reparafacil.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba2.reparafacil.data.local.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UnderSessionViewModel(context: Context) : ViewModel() {

    private val session = SessionManager(context)

    // 🔹 Guardar token (suspend)
    fun saveToken(token: String) {
        viewModelScope.launch {
            session.saveToken(token)
        }
    }

    // 🔹 Obtener token (Flow)
    fun getToken(): Flow<String?> {
        return session.token
    }

    // 🔹 Borrar sesión (suspend)
    fun clearSession() {
        viewModelScope.launch {
            session.clearSession()
        }
    }
}