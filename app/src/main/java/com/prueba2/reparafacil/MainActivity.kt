package com.prueba2.reparafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.prueba2.reparafacil.ui.navegation.AppNavHost
import com.prueba2.reparafacil.ui.theme.ReparaFacilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReparaFacilTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}