package com.prueba2.reparafacil.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.prueba2.reparafacil.ui.di.AppDependencies

// SHARED
import com.prueba2.reparafacil.ui.screens.shared.NotificacionesScreen
import com.prueba2.reparafacil.ui.screens.shared.DetalleServicioScreen
import com.prueba2.reparafacil.ui.screens.shared.SeguimientoServicioScreen

// LOGIN + REGISTER
import com.prueba2.reparafacil.ui.screens.login.LoginScreen
import com.prueba2.reparafacil.ui.screens.login.RegisterScreen
import com.prueba2.reparafacil.ui.viewmodel.login.LoginViewModel
import com.prueba2.reparafacil.ui.viewmodel.login.LoginViewModelFactory
import com.prueba2.reparafacil.ui.viewmodel.register.RegisterViewModel
import com.prueba2.reparafacil.ui.viewmodel.register.RegisterViewModelFactory

// PROFILE
import com.prueba2.reparafacil.ui.screens.profile.ProfileScreen

// CLIENTE
import com.prueba2.reparafacil.ui.screens.cliente.HomeClienteScreen
import com.prueba2.reparafacil.ui.screens.cliente.CrearServicioScreen
import com.prueba2.reparafacil.ui.screens.cliente.ListaTecnicosScreen
import com.prueba2.reparafacil.ui.screens.cliente.AgendaScreen
import com.prueba2.reparafacil.ui.screens.cliente.AgendarCitaScreen
import com.prueba2.reparafacil.ui.screens.cliente.MisReparacionesScreen

// TECNICO
import com.prueba2.reparafacil.ui.screens.tecnico.HomeTecnicoScreen
import com.prueba2.reparafacil.ui.screens.tecnico.ReparacionesAsignadasScreen
import com.prueba2.reparafacil.ui.screens.tecnico.DetalleReparacionScreen

import com.prueba2.reparafacil.data.remote.dto.servicio.CrearServicioRequest
import com.prueba2.reparafacil.data.remote.dto.servicio.ServicioDto
import com.prueba2.reparafacil.data.remote.dto.user.UserDto

// VM Tecnicos
import com.prueba2.reparafacil.ui.viewmodel.cliente.ListaTecnicosViewModel
import com.prueba2.reparafacil.ui.viewmodel.cliente.ListaTecnicosViewModelFactory

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route,
        modifier = modifier
    ) {

        composable(Routes.Login.route) {
            val context = LocalContext.current
            val userRepo = AppDependencies.provideUserRepository(context)
            val vm: LoginViewModel = viewModel(factory = LoginViewModelFactory(userRepo))

            LoginScreen(
                state = vm.state,
                onEmailChange = vm::onEmailChange,
                onPasswordChange = vm::onPasswordChange,
                onLoginClick = {
                    vm.login { rol ->
                        val destino = if (rol.uppercase() == "TECNICO") {
                            Routes.HomeTecnico.route
                        } else {
                            Routes.HomeCliente.route
                        }
                        navController.navigate(destino) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.Register.route) }
            )
        }

        composable(Routes.Register.route) {
            val context = LocalContext.current
            val userRepo = AppDependencies.provideUserRepository(context)
            val vm: RegisterViewModel = viewModel(factory = RegisterViewModelFactory(userRepo))

            RegisterScreen(
                state = vm.state,
                onNombreChange = vm::onNombreChange,
                onEmailChange = vm::onEmailChange,
                onPasswordChange = vm::onPasswordChange,
                onRolChange = vm::onRolChange,
                onCertificacionInputChange = vm::onCertificacionInputChange,
                onAddCertificacion = vm::addCertificacion,
                onRemoveCertificacion = vm::removeCertificacion,
                onRegisterClick = {
                    vm.register { rol ->
                        val destino = if (rol.uppercase() == "TECNICO") {
                            Routes.HomeTecnico.route
                        } else {
                            Routes.HomeCliente.route
                        }
                        navController.navigate(destino) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen(
                user = null,
                onLogout = {
                    navController.navigate(Routes.Login.route) { popUpTo(0) }
                }
            )
        }

        // ---------------- CLIENTE ----------------
        composable(Routes.HomeCliente.route) {
            HomeClienteScreen(
                onCrearServicio = { navController.navigate(Routes.CrearServicio.route) },
                onVerTecnicos = { navController.navigate(Routes.ListaTecnicos.route) },
                onMisReparaciones = { navController.navigate(Routes.MisReparaciones.route) },
                onNotificaciones = { navController.navigate(Routes.Notificaciones.route) }
            )
        }

        composable(Routes.CrearServicio.route) {
            CrearServicioScreen(
                loading = false,
                errorMessage = null,
                onSubmit = { _: CrearServicioRequest ->
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ListaTecnicos.route) {
            val context = LocalContext.current
            val repo = AppDependencies.provideTecnicoRepository(context)
            val vm: ListaTecnicosViewModel = viewModel(factory = ListaTecnicosViewModelFactory(repo))
            val state by vm.state.collectAsState()

            LaunchedEffect(Unit) { vm.cargarTecnicos() }

            ListaTecnicosScreen(
                tecnicos = state.tecnicos,
                loading = state.isLoading,
                errorMessage = state.errorMessage,
                onRefresh = { vm.cargarTecnicos() },
                onSelect = { /* TODO */ },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.AgendaCliente.route) {
            AgendaScreen(
                citas = emptyList(),
                loading = false,
                errorMessage = null,
                onRefresh = { /* TODO */ },
                onBack = { navController.popBackStack() }
            )
        }

        // ✅ AGENDAR CITA (Mongo ID = String)
        composable(Routes.AgendarCita.route) { backStackEntry ->
            val servicioId = backStackEntry.arguments?.getString("servicioId")
                ?: return@composable

            AgendarCitaScreen(
                servicioId = servicioId,
                loading = false,
                errorMessage = null,
                onSubmit = {
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.MisReparaciones.route) {
            MisReparacionesScreen(
                servicios = emptyList(),
                onSelect = { servicio ->
                    navController.navigate(Routes.DetalleServicio.create(servicio.id))
                }
            )
        }

        // ---------------- TECNICO ----------------
        composable(Routes.HomeTecnico.route) {
            HomeTecnicoScreen(
                onVerReparaciones = { navController.navigate(Routes.ReparacionesAsignadas.route) },
                onMiAgenda = { navController.navigate(Routes.AgendaCliente.route) },
                onNotificaciones = { navController.navigate(Routes.Notificaciones.route) }
            )
        }

        composable(Routes.ReparacionesAsignadas.route) {
            ReparacionesAsignadasScreen(
                servicios = emptyList(),
                onSelect = { servicio ->
                    navController.navigate(Routes.DetalleReparacion.create(servicio.id))
                }
            )
        }

        composable(Routes.DetalleReparacion.route) {
            DetalleReparacionScreen(
                servicio = ServicioDto(
                    id = "0",
                    titulo = "Pendiente",
                    descripcion = "Conectar backend",
                    direccion = "-",
                    estado = "-",
                    fechaSolicitud = "-",
                    cliente = UserDto(id = "0", email = "demo@demo.com", rol = "CLIENTE"),
                    tecnico = null,
                    garantia = null
                ),
                onFinalizar = { navController.popBackStack() }
            )
        }

        composable(Routes.Notificaciones.route) {
            NotificacionesScreen(onBack = { navController.popBackStack() })
        }

        // ✅ DETALLE SERVICIO (String)
        composable(Routes.DetalleServicio.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetalleServicioScreen(
                servicioId = id,
                onBack = { navController.popBackStack() },
                onVerSeguimiento = { sid ->
                    navController.navigate(Routes.SeguimientoServicio.create(sid))
                }
            )
        }

        // ✅ SEGUIMIENTO (String)
        composable(Routes.SeguimientoServicio.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            SeguimientoServicioScreen(
                servicioId = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
