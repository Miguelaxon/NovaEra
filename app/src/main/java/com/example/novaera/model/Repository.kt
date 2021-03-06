package com.example.novaera.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.novaera.local.ClassNovaEra
import com.example.novaera.local.IDao
import com.example.novaera.local.NovaEra
import com.example.novaera.remote.ApiClient

class Repository(private val iDao: IDao) {
    val listAll: LiveData<List<ClassNovaEra>> = iDao.getAllList()

    fun converterList(list: List<NovaEra>): List<ClassNovaEra>{
        val listM: MutableList<ClassNovaEra> = mutableListOf()
        list.map {
            listM.add(ClassNovaEra(id = it.id, name = it.name, price = it.price,
            image = it.image))
        }
        return listM
    }

    suspend fun getFetchCoroutines(){
        try {
            val response = ApiClient.getApiClient().getFetchList()
            when (response.isSuccessful){
                true -> response.body()?.let {
                    iDao.insertAllList(it)
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        } catch (t: Throwable){
            Log.d("Error Books Coroutine", t.message.toString())
        }
    }
}