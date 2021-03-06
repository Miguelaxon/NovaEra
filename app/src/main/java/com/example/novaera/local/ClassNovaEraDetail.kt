package com.example.novaera.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_detail")
data class ClassNovaEraDetail (@PrimaryKey val id: Int,
                               val name: String,
                               val price: Int,
                               val image: String,
                               val description: String,
                               val lastPrice: Int,
                               val credit: Boolean)