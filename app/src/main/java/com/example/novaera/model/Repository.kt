package com.example.novaera.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.novaera.local.ClassNovaEra
import com.example.novaera.local.ClassNovaEraDetail
import com.example.novaera.local.IDao
import com.example.novaera.local.NovaEra
import com.example.novaera.remote.ApiClient

class Repository(private val iDao: IDao) {
    val listAll: LiveData<List<ClassNovaEra>> = iDao.getAllList()

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

    fun converterDescription(id: Int, name: String, price: Int, image: String, description: String,
                             lastPrice: Int, credit: Boolean): List<ClassNovaEraDetail>{
        val listM: MutableList<ClassNovaEraDetail> = mutableListOf()
        listM.add(ClassNovaEraDetail(id = id, name =  name, price =  price, image =  image,
                description =  description, lastPrice =  lastPrice, credit =  credit))
        return listM
    }

    suspend fun getFetchCoroutinesId(id: Int){
        try {
            val response = ApiClient.getApiClient().getFetchDetail(id)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    iDao.insertAllDetail(converterDescription(id, name = it.name, price = it.price,
                            image = it.image, description = it.description, lastPrice = it.lastPrice,
                            credit = it.credit))
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        } catch (t: Throwable){
            Log.d("Error Books Coroutine", t.message.toString())
        }
    }

    fun getDetail(id: Int): LiveData<List<ClassNovaEraDetail>> = iDao.getAllDetail(id)

    fun getDetail2(id: Int): LiveData<ClassNovaEraDetail> = iDao.getAllDetail2(id)
}