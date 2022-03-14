package com.example.calculater.data.roomdatabase.calculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculateDataDao {

    @Query("SELECT * FROM calculate_data")
    fun getData(): LiveData<List<CalculateDataItem>?>

    @Insert
    suspend fun insertData(calculateDataItem: CalculateDataItem)


}