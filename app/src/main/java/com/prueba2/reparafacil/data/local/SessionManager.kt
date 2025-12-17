package com.prueba2.reparafacil.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class SessionManager(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
    }

    // TOKEN
    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs -> prefs[TOKEN_KEY] = token }
    }

    val token: Flow<String?> = context.dataStore.data.map { prefs -> prefs[TOKEN_KEY] }

    suspend fun getToken(): String? =
        context.dataStore.data.map { it[TOKEN_KEY] }.firstOrNull()

    // USER ID
    suspend fun saveUserId(id: String) {
        context.dataStore.edit { prefs -> prefs[USER_ID_KEY] = id }
    }

    val userId: Flow<String?> = context.dataStore.data.map { prefs -> prefs[USER_ID_KEY] }

    // ROLE
    suspend fun saveUserRole(role: String) {
        context.dataStore.edit { prefs -> prefs[USER_ROLE_KEY] = role }
    }

    val userRole: Flow<String?> = context.dataStore.data.map { prefs -> prefs[USER_ROLE_KEY] }

    suspend fun getUserRole(): String? =
        context.dataStore.data.map { it[USER_ROLE_KEY] }.firstOrNull()

    // CLEAR
    suspend fun clearSession() {
        context.dataStore.edit { prefs -> prefs.clear() }
    }
}
