package com.example.calculater.data.datamanager

import com.meril.merilcovifind.data.roomdatabase.AppDatabase

interface IDataManager {
    fun getDatabase(): AppDatabase
}