package com.example.calculater.base

import androidx.lifecycle.ViewModel
import com.example.calculater.data.datamanager.DataManager
import com.meril.merilcovifind.data.roomdatabase.AppDatabase

open class BaseViewModel : ViewModel() {

    fun getDatabase(): AppDatabase {
        return DataManager.getInstance().getDatabase()
    }

}