package com.example.novaera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.novaera.local.BaseDatos
import com.example.novaera.local.ClassNovaEra
import com.example.novaera.model.Repository
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    private val repository: Repository
    val listAll: LiveData<List<ClassNovaEra>>
    private var idM: Int = 0

    init {
        val baseDatos = BaseDatos.getDataBase(application).getIDAO()
        repository = Repository(baseDatos)
        viewModelScope.launch {
            repository.getFetchCoroutines()
        }
        listAll = repository.listAll
    }

    fun selectedDetail(id: Int) = viewModelScope.launch {
        idM = id
        repository.getFetchCoroutinesId(id)
    }
}