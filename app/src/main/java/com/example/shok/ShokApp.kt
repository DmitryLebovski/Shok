package com.example.shok

import android.app.Application
import com.example.shok.components.AppComponent
import com.example.shok.components.DaggerAppComponent

class ShokApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}