package com.prueba2.reparafacil.ui.viewmodel.cliente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prueba2.reparafacil.data.repository.TecnicoRepository

class ListaTecnicosViewModelFactory(
    private val repo: TecnicoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaTecnicosViewModel::class.java)) {
            return ListaTecnicosViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
