package com.example.novaera.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_list")
data class ClassNovaEra(@PrimaryKey @SerializedName("id") val id: Int,
                        @SerializedName("name") val name: String,
                        @SerializedName("price") val price: Int,
                        @SerializedName("image") val image: String)
