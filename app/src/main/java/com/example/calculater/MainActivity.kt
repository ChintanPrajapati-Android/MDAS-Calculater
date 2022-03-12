package com.example.calculater

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private var tempDigit: String? = null
    private var result: String? = null
    private var tmp: String? = null
    private var operator: String? = null

    private val input = ArrayList<String>()
    private var x = 0.0
    private var y = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn0.setOnClickListener { onNumberButtonClicked("0") }
        btn1.setOnClickListener { onNumberButtonClicked("1") }
        btn2.setOnClickListener { onNumberButtonClicked("2") }
        btn3.setOnClickListener { onNumberButtonClicked("3") }
        btn4.setOnClickListener { onNumberButtonClicked("4") }
        btn5.setOnClickListener { onNumberButtonClicked("5") }
        btn6.setOnClickListener { onNumberButtonClicked("6") }
        btn7.setOnClickListener { onNumberButtonClicked("7") }
        btn8.setOnClickListener { onNumberButtonClicked("8") }
        btn9.setOnClickListener { onNumberButtonClicked("9") }
        btnClear.setOnClickListener { onClearButtonClicked() }
        btnMinus.setOnClickListener { onOperatorButtonClicked("-") }
        btnPlus.setOnClickListener { onOperatorButtonClicked("+") }
        btnMulti.setOnClickListener { onOperatorButtonClicked("X") }
        btnDiv.setOnClickListener { onOperatorButtonClicked("/") }
        btnEqual.setOnClickListener {
            tempDigit?.let {
                input.add(it)
                tempDigit = null
            }
            tvInputHint.text = TextUtils.join("", input)
            val exp = TextUtils.join("", input)
            val expression = ExpressionBuilder(exp).build()
            val result = expression.evaluate()
            tvInput .text = result.toString()
        }
    }


    private fun onOperatorButtonClicked(operator: String) {
        this.operator = operator
        tempDigit?.let {
            input.add(it)
            tempDigit = null
        }
        tvInput.text = ""
        tvInputHint.text = TextUtils.join("", input)
    }

    private fun onClearButtonClicked() {
        tvInput.text = ""
        tvInputHint.text = ""
        tempDigit = null
        operator = null
        input.clear()
    }

    private fun onNumberButtonClicked(number: String) {
        tempDigit = tvInput.text.toString()
        tempDigit += number
        tvInput.text = tempDigit
        operator?.let {
            input.add(it)
            operator = null
        }
        tvInputHint.text = TextUtils.join("", input)
    }
    
}