package com.example.calculater.data.roomdatabase.calculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "calculate_data")
data class CalculateDataItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "equation") var equation: String = "",
    @ColumnInfo(name = "total") var total: String = ""
) : Serializable