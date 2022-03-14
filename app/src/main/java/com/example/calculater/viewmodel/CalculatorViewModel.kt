package com.example.calculater.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.calculater.base.BaseViewModel
import com.example.calculater.data.roomdatabase.calculator.CalculateDataItem
import com.example.calculater.extra.MADSCalculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToLong

class CalculatorViewModel : BaseViewModel() {

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
        viewModelScope.launch {
            getDatabase().getCalculateDataDao().insertData(
                CalculateDataItem(
                    equation = input,
                    total = result.roundToLong().toString()
                )
            )
            withContext(Dispatchers.Main) {
                mEvaluateData.postValue(result)
            }
        }
    }

    fun getData() : LiveData<List<CalculateDataItem>?> {
        return getDatabase().getCalculateDataDao().getData()
    }
}