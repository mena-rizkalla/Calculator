package com.example.calculator.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculator.model.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface CalculatorDao {

    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item")
    fun calculations() : Flow<List<Item>>

}