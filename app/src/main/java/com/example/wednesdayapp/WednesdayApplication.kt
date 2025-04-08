package com.example.wednesdayapp

import android.app.Application
import com.example.wednesdayapp.data.AppContainer
import com.example.wednesdayapp.data.DefaultAppContainer

class WednesdayApplication : Application() {
    /**AppContainer instance used by rest of classes to obtain dependencies**/
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}