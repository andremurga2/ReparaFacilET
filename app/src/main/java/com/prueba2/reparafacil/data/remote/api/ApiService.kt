package com.prueba2.reparafacil.data.remote.api

import android.content.Context
import com.prueba2.reparafacil.data.local.SessionManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val BASE_URL = "https://reparafacil.onrender.com/api/"

    @Volatile private var retrofit: Retrofit? = null

    fun build(context: Context) {
        val sessionManager = SessionManager(context)

        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(sessionManager)) // ✅ JWT automático
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun requireRetrofit(): Retrofit {
        return retrofit ?: throw IllegalStateException(
            "ApiService no inicializado. Llama ApiService.build(context) en AppDependencies o Application."
        )
    }

    val authApi: AuthApi by lazy { requireRetrofit().create(AuthApi::class.java) }
    val servicioApi: ServicioApi by lazy { requireRetrofit().create(ServicioApi::class.java) }
    val tecnicoApi: TecnicoApi by lazy { requireRetrofit().create(TecnicoApi::class.java) }
    val agendaApi: AgendaApi by lazy { requireRetrofit().create(AgendaApi::class.java) }
    val garantiaApi: GarantiaApi by lazy { requireRetrofit().create(GarantiaApi::class.java) }
    val notificacionesApi: NotificacionesApi by lazy { requireRetrofit().create(NotificacionesApi::class.java) }
}
