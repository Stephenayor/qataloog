package com.example.qataloog.base

import androidx.fragment.app.Fragment
import com.example.qataloog.di.DaggerqataloogAppComponent
import com.example.qataloog.di.qataloogAppComponent

abstract class BaseFragment(): Fragment() {

    protected val qataloogAppComponent: qataloogAppComponent
    get() =  DaggerqataloogAppComponent.builder()

        .build()

}