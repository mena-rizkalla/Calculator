package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculator.dao.CalculatorDao
import com.example.calculator.model.CalculatorState
import com.example.calculator.model.Item
import kotlinx.coroutines.launch

class CalculatorViewModel(private val dao: CalculatorDao): ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.number)
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Clear -> state = CalculatorState()
            is CalculatorActions.Operation -> enterOperation(action.operation)
            is CalculatorActions.Calculate -> perfomCalculation()
            is CalculatorActions.Delete -> delete()
        }
    }

    private fun enterNumber(number: Int) {
       if (state.operation == null){
           state = state.copy(number1 = state.number1 + number)
       }else{
           state =state.copy(number2 = state.number2 + number)
       }
    }

    private fun delete() {
       when{
           state.number2.isNotBlank() -> state = state.copy(number2 = state.number2.dropLast(1))
           state.operation != null -> state = state.copy(operation = null)
           state.number1.isNotBlank() -> state = state.copy(number1 = state.number1.dropLast(1))
       }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null
            && !state.number1.contains(".")
            && state.number1.isNotBlank()){
            state = state.copy(number1 = state.number1 + ".")
            return
        }

        if (state.operation != null
            && !state.number2.contains(".")
            && state.number2.isNotBlank()){
            state = state.copy(number2 = state.number2 + ".")
            return
        }
    }

    private fun perfomCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Division -> number1 / number2
                null -> return
            }
            val operation = when (state.operation) {
                is CalculatorOperation.Add -> "+"
                is CalculatorOperation.Subtract -> "-"
                is CalculatorOperation.Multiply -> "*"
                is CalculatorOperation.Division -> "/"
                null -> return
            }
            val item = "$number1  ${operation}  $number2 = $result"
            val it = Item(result = item)
            viewModelScope.launch {
                dao.insert(it)
            }
            state = state.copy(number1 = result.toString() ,
                operation = null , number2 = "")
        }

    }



}