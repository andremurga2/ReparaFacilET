package com.prueba2.reparafacil.ui.navegation

sealed class Routes(val route: String) {

    object Login : Routes("login")
    object Register : Routes("register")
    object Profile : Routes("profile")

    // Cliente
    object HomeCliente : Routes("home_cliente")
    object CrearServicio : Routes("crear_servicio")
    object ListaTecnicos : Routes("lista_tecnicos")
    object AgendaCliente : Routes("agenda_cliente")
    object AgendarCita : Routes("agendar_cita/{servicioId}") {
        fun create(servicioId: String) = "agendar_cita/$servicioId"
    }
    object MisReparaciones : Routes("mis_reparaciones")

    // Técnico
    object HomeTecnico : Routes("home_tecnico")
    object ReparacionesAsignadas : Routes("reparaciones_asignadas")

    object DetalleReparacion : Routes("detalle_reparacion/{id}") {
        fun create(id: String) = "detalle_reparacion/$id"
    }

    // Shared
    object Notificaciones : Routes("notificaciones")
    object DetalleServicio : Routes("detalle_servicio/{id}") {
        fun create(id: String) = "detalle_servicio/$id"
    }

    object SeguimientoServicio : Routes("seguimiento_servicio/{id}") {
        fun create(id: String) = "seguimiento_servicio/$id"
    }
}
