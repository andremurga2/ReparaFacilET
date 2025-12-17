package com.prueba2.reparafacil.ui.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prueba2.reparafacil.data.repository.UserRepository

class RegisterViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
