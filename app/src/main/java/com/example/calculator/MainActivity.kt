package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var txtDisplay: TextView
    private lateinit var historyContainer: LinearLayout

    private var currentInput: String = ""
    private var operator: String? = null
    private var firstNumber: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyContainer = findViewById(R.id.history_container)
        txtDisplay = findViewById(R.id.txt_display)

        val btn0: Button = findViewById(R.id.btn_0)
        val btn1: Button = findViewById(R.id.btn_1)
        val btn2: Button = findViewById(R.id.btn_2)
        val btn3: Button = findViewById(R.id.btn_3)
        val btn4: Button = findViewById(R.id.btn_4)
        val btn5: Button = findViewById(R.id.btn_5)
        val btn6: Button = findViewById(R.id.btn_6)
        val btn7: Button = findViewById(R.id.btn_7)
        val btn8: Button = findViewById(R.id.btn_8)
        val btn9: Button = findViewById(R.id.btn_9)

        val btnAdd: Button = findViewById(R.id.btn_add)
        val btnSub: Button = findViewById(R.id.btn_sub)
        val btnMul: Button = findViewById(R.id.btn_mul)
        val btnDiv: Button = findViewById(R.id.btn_div)
        val btnSqrt: Button = findViewById(R.id.btn_sqrt)

        val btnClear: Button = findViewById(R.id.btn_clear)
        val btnBack: Button = findViewById(R.id.btn_back)
        val btnSign: Button = findViewById(R.id.btn_sign)
        val btnEqual: Button = findViewById(R.id.btn_equal)

        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                currentInput += index.toString()
                txtDisplay.text = currentInput
            }
        }

        btnAdd.setOnClickListener { setOperator("+") }
        btnSub.setOnClickListener { setOperator("-") }
        btnMul.setOnClickListener { setOperator("*") }
        btnDiv.setOnClickListener { setOperator("/") }

        btnSqrt.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                val value = currentInput.toDouble()
                val result = sqrt(value)
                txtDisplay.text = result.toString()


                addToHistory("√$value = $result")

                currentInput = result.toString()
            }
        }

        btnClear.setOnClickListener {
            currentInput = ""
            operator = null
            firstNumber = null
            txtDisplay.text = ""


            historyContainer.removeAllViews()
        }

        btnBack.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                txtDisplay.text = currentInput
            }
        }

        btnSign.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = if (currentInput.startsWith("-")) {
                    currentInput.drop(1)
                } else {
                    "-$currentInput"
                }
                txtDisplay.text = currentInput
            }
        }

        btnEqual.setOnClickListener { calculateResult() }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (firstNumber != null && operator != null && currentInput.isNotEmpty()) {

            val secondNumber = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstNumber!! + secondNumber
                "-" -> firstNumber!! - secondNumber
                "*" -> firstNumber!! * secondNumber
                "/" -> firstNumber!! / secondNumber
                else -> 0.0
            }

            txtDisplay.text = result.toString()


            addToHistory("${firstNumber} $operator $secondNumber = $result")

            currentInput = result.toString()
            operator = null
            firstNumber = null
        }
    }

    private fun addToHistory(text: String) {
        val entry = TextView(this)
        entry.text = text
        entry.textSize = 18f
        historyContainer.addView(entry)
    }
}
