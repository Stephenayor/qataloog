package com.example.qataloog.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.qataloog.base.BaseViewmodel
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedBooksDao
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedDatabase
import com.example.qataloog.model.responsemodel.dashboard.DashBoardList
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import com.example.qataloog.repository.DashBoardListRepository
import javax.inject.Inject

class DashBoardListViewModel @Inject constructor(
    private val dashBoardListRepository: DashBoardListRepository,
)  : BaseViewmodel() {

//    private val dashBoardListRepository: DashBoardListRepository = DashBoardListRepository()


    init{
//        (application as MyApp).getQataloogAppComponent().inject(this)
    }

    fun getDashBoardListData(preferences: SharedPreferences): LiveData<DashBoardList> {
         return dashBoardListRepository.getDashBoardListData(preferences)
    }

     fun getRecommendedBooks(preferences: SharedPreferences, recommendedDatabase: RecommendedDatabase) :
             LiveData<List<Recommended>> {
        val recommendedBooksDao = recommendedDatabase.recommendedBooksDao
        dashBoardListRepository.getDashBoardListData(preferences)
        return recommendedBooksDao.getAllRecommendedBooks()
    }

}