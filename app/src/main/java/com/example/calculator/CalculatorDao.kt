package com.example.calculator

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CalculatorDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item")
    fun calculations() : Flow<List<Item>>

}