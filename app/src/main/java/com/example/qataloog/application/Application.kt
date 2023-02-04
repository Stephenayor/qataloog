package com.example.qataloog.application

import android.app.Application
import com.example.qataloog.di.AppModule
import com.example.qataloog.di.DaggerqataloogAppComponent
import com.example.qataloog.network.WifiService
import javax.inject.Inject

//open class Application @Inject constructor() : Application() {
//    companion object {
//        lateinit var instance:  com.example.qataloog.application.Application
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//
////       DaggerqataloogAppComponent.builder()
////            .appModule(AppModule(this))
////            .build()
//    }
//
//}