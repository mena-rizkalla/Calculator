package com.example.calculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculator.dao.CalculatorDao
import com.example.calculator.model.Item

@Database(entities = [Item::class] , version = 1 , exportSchema = false)
abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun CalculatorDao(): CalculatorDao

    companion object {
        @Volatile
        private var Instance: CalculatorDatabase? = null

        fun getDatabase(context: Context): CalculatorDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CalculatorDatabase::class.java, "calculator_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}