package com.example.testfilms

import android.app.Application
import com.example.testfilms.di.AppComponent
import com.example.testfilms.di.DaggerAppComponent


class App : Application() {
    lateinit var applicationComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder().build()
    }

}