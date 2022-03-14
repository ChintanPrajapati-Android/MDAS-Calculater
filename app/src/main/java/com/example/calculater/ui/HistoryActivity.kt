package com.example.calculater.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculater.R
import com.example.calculater.data.roomdatabase.calculator.CalculateDataItem
import com.example.calculater.ui.adapter.HistoryAdapter
import com.example.calculater.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_history.*


class HistoryActivity : AppCompatActivity() {

    private lateinit var calculatorViewModel: CalculatorViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]

        ivBack.setOnClickListener {
            onBackPressed()
        }
        rvData.layoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter()
        rvData.adapter = adapter
        calculatorViewModel.getData().observe(this) {
            it?.let {
                adapter.addData(it as ArrayList<CalculateDataItem>)
            }
        }
    }
}