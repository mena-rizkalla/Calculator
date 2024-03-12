package com.example.calculator.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.dao.CalculatorDao
import com.example.calculator.CalculatorViewModel

class CalculatorViewModelFactory(private val dao: CalculatorDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}