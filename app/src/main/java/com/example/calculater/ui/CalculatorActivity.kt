package com.example.calculater.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.calculater.R
import com.example.calculater.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlin.math.roundToLong


class CalculatorActivity : AppCompatActivity() {

    private lateinit var calculatorViewModel: CalculatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        calculatorViewModel.mEvaluateData.observe(this) {
            tvInput.text = it.roundToLong().toString()
            if (calculatorViewModel.input.isNotEmpty()){
                val exp = TextUtils.join("", calculatorViewModel.input)
                tvInputHint.text = exp
            }
        }


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
            calculatorViewModel.tempDigit?.let {
                calculatorViewModel.input.add(it)
                calculatorViewModel.tempDigit = null
            }
            val exp = TextUtils.join("", calculatorViewModel.input)
            tvInputHint.text = exp
            calculatorViewModel.evaluate(exp)
        }
    }


    private fun onOperatorButtonClicked(operator: String) {
        calculatorViewModel.operator = operator
        calculatorViewModel.tempDigit?.let {
            calculatorViewModel.input.add(it)
            calculatorViewModel.tempDigit = null
        }
        tvInput.text = ""
        tvInputHint.text = TextUtils.join("", calculatorViewModel.input)
    }

    private fun onClearButtonClicked() {
        tvInput.text = ""
        tvInputHint.text = ""
        calculatorViewModel.tempDigit = null
        calculatorViewModel.operator = null
        calculatorViewModel.input.clear()
    }

    private fun onNumberButtonClicked(number: String) {
        if (calculatorViewModel.tempDigit != null) {
            if (calculatorViewModel.tempDigit?.length ?: 0 >= 9) {
                return
            }
        }
        calculatorViewModel.tempDigit = tvInput.text.toString()
        calculatorViewModel.tempDigit += number
        tvInput.text = calculatorViewModel.tempDigit
        calculatorViewModel.operator?.let {
            if (calculatorViewModel.input.isEmpty().not()) {
                calculatorViewModel.input.add(it)
            }
            calculatorViewModel.operator = null
        }
        tvInputHint.text = TextUtils.join("", calculatorViewModel.input)
    }
}