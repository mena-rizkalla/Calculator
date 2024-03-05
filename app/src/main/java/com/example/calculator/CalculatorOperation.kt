package com.example.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Division: CalculatorOperation("/")
    object Multiply: CalculatorOperation("*")

}