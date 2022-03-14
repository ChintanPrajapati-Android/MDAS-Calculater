package com.example.calculater.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculater.extra.MADSCalculator

class CalculatorViewModel : ViewModel() {

    val mEvaluateData = MutableLiveData<Float>()
    var tempDigit: String? = null
    var operator: String? = null
    val input = ArrayList<String>()

    fun evaluate(input: String) {
        val value = input.replace("X", "*")
        val madsCalculator = MADSCalculator()
        val postfixExpression: String = madsCalculator.findPostfix(value)
        if (postfixExpression == "Invalid expression") {
            return
        }
        val result: Float = madsCalculator.evaluatePostfix(postfixExpression)
        mEvaluateData.postValue(result)
    }
}