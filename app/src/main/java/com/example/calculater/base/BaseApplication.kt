package com.example.calculater.base

import android.app.Application


class BaseApplication : Application() {
    companion object {
        @get:Synchronized
        lateinit var SINGLETON_INSTANCE: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        SINGLETON_INSTANCE = this
    }
}