package com.example.qataloog.di

import android.app.Application
import javax.inject.Inject

class MyApp @Inject constructor() : Application() {
    private lateinit var qataloogAppComponent: qataloogAppComponent

    override fun onCreate() {
        super.onCreate()
        qataloogAppComponent = DaggerqataloogAppComponent.builder()

                                .build()
    }

    fun getQataloogAppComponent(): qataloogAppComponent{
        return qataloogAppComponent
    }
}