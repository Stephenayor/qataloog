package com.example.qataloog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qataloog.viewmodel.LoginViewModel
import com.example.qataloog.viewmodel.LoginViewmodelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class LoginViewmodelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: LoginViewmodelFactory
    ): ViewModelProvider.Factory

    @Binds
    abstract fun bindContactSourcesViewModel(viewModel: LoginViewModel): ViewModel
}