package com.example.calculater.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculater.R
import com.example.calculater.data.roomdatabase.calculator.CalculateDataItem
import kotlinx.android.synthetic.main.lay_data_item.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {
    private var alData = ArrayList<CalculateDataItem>()

    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lay_data_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val item = alData[position]
        holder.itemView.tvEquation.text = item.equation
        holder.itemView.tvTotal.text = item.total
    }

    override fun getItemCount(): Int {
        return alData.size
    }

    fun addData(data: ArrayList<CalculateDataItem>) {
        this.alData = data
        notifyDataSetChanged()
    }
}