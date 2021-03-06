package com.example.novaera.local

import com.google.gson.annotations.SerializedName

data class NovaEra(@SerializedName("id") val id: Int,
                   @SerializedName("name") val name: String,
                   @SerializedName("price") val price: Int,
                   @SerializedName("image") val image: String)
