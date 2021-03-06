package com.example.novaera.remote

import com.example.novaera.local.ClassNovaEra
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IApi {
    @GET("products")
    suspend fun getFetchList(): Response<List<ClassNovaEra>>

    @GET("details/{id}")
    suspend fun getFetchDetail(@Path("id")id: Int): Response<NovaDetail>
}