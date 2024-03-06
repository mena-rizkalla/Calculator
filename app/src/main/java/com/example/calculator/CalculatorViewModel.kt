package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

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
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    private fun perfomCalculation() {
        TODO("Not yet implemented")
    }

}