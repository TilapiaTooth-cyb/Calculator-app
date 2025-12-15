package com.example.calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class CalculatorEngineTest {

    private val engine = CalculatorEngine()

    @Test
    fun calculate_addition_returnsCorrectSum() {
        val result = engine.calculate(5.0, "+", 3.0)
        assertEquals(8.0, result, 0.001)
    }

    @Test
    fun calculate_subtraction_returnsCorrectDifference() {
        val result = engine.calculate(10.5, "-", 4.0)
        assertEquals(6.5, result, 0.001)
    }

    @Test
    fun calculate_multiplication_returnsCorrectProduct() {
        val result = engine.calculate(4.0, "*", 2.5)
        assertEquals(10.0, result, 0.001)
    }

    @Test
    fun calculate_division_returnsCorrectQuotient() {
        val result = engine.calculate(9.0, "/", 3.0)
        assertEquals(3.0, result, 0.001)
    }

    @Test // Removed (expected = ...)
    fun calculate_divisionByZero_throwsException() {
        // Use assertThrows for JUnit 5
        assertThrows(ArithmeticException::class.java) {
            engine.calculate(5.0, "/", 0.0)
        }
    }

    @Test
    fun calculate_withNegativeNumbers_returnsCorrectResult() {
        val result = engine.calculate(-5.0, "+", 2.0)
        assertEquals(-3.0, result, 0.001)
    }

    @Test
    fun squareRoot_positiveNumber_returnsCorrectResult() {
        val result = engine.squareRoot(25.0)
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun squareRoot_zero_returnsZero() {
        val result = engine.squareRoot(0.0)
        assertEquals(0.0, result, 0.001)
    }

    @Test // Removed (expected = ...)
    fun squareRoot_negativeNumber_throwsException() {
        // Use assertThrows for JUnit 5
        assertThrows(IllegalArgumentException::class.java) {
            engine.squareRoot(-9.0)
        }
    }

    @Test
    fun toggleSign_positiveToNegative() {
        val result = engine.toggleSign(10.0)
        assertEquals(-10.0, result, 0.001)
    }

    @Test
    fun toggleSign_negativeToPositive() {
        val result = engine.toggleSign(-5.0)
        assertEquals(5.0, result, 0.001)
    }
}
