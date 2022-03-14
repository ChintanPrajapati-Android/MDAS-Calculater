package com.example.calculater.data.datamanager

import com.meril.merilcovifind.data.roomdatabase.AppDatabase

class DataManager : IDataManager {

    companion object {
        private var SINGLETON_INSTANCE: IDataManager? = null
        fun getInstance(): IDataManager {
            if (SINGLETON_INSTANCE == null) {
                SINGLETON_INSTANCE = DataManager()
            }
            return SINGLETON_INSTANCE!!
        }

    }

    override fun getDatabase(): AppDatabase {
        return AppDatabase.getDatabase()
    }
}