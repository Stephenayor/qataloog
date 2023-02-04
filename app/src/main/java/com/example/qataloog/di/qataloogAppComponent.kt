package com.example.qataloog.di

import com.example.qataloog.viewmodel.BooksSearchViewmodel
import com.example.qataloog.viewmodel.DashBoardListViewModel
import com.example.qataloog.viewmodel.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface qataloogAppComponent {


    fun getLoginViewModel(): LoginViewModel

    fun getBooksSearchViewModel(): BooksSearchViewmodel

    fun getDashBoardListViewModel(): DashBoardListViewModel

//    fun inject(dashBoardListViewModel: DashBoardListViewModel)

//    fun getRecommendedDataBase(): RecommendedDatabase

}