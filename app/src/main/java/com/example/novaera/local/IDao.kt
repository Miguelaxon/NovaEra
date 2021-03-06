package com.example.novaera.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(cell: List<ClassNovaEra>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetail(cell: List<ClassNovaEraDetail>)

    @Query("SELECT * FROM table_list")
    fun getAllList(): LiveData<List<ClassNovaEra>>
}