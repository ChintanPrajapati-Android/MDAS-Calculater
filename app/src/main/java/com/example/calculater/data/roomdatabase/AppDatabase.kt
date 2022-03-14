package com.meril.merilcovifind.data.roomdatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calculater.base.BaseApplication
import com.example.calculater.data.roomdatabase.calculator.CalculateDataDao
import com.example.calculater.data.roomdatabase.calculator.CalculateDataItem

@Database(
    entities = [CalculateDataItem::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var SINGLETON_INSTANCE: AppDatabase? = null


        fun getDatabase(): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (SINGLETON_INSTANCE == null) {
                    SINGLETON_INSTANCE = Room.databaseBuilder(
                        BaseApplication.SINGLETON_INSTANCE,
                        AppDatabase::class.java, "scf"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return SINGLETON_INSTANCE!!
        }
    }

    abstract fun getCalculateDataDao(): CalculateDataDao
}
