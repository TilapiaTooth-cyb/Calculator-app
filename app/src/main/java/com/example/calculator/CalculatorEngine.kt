package com.example.calculator

import kotlin.math.sqrt

class CalculatorEngine {

    fun calculate(firstNumber: Double, operator: String, secondNumber: Double): Double {
        return when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    throw ArithmeticException("Division by zero")
                }
                firstNumber / secondNumber
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    fun squareRoot(value: Double): Double {
        if (value < 0) {
            throw IllegalArgumentException("Cannot calculate square root of a negative number")
        }
        return sqrt(value)
    }

    fun toggleSign(value: Double): Double {
        return -value
    }
}