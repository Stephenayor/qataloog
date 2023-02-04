package com.example.qataloog.repository

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.qataloog.base.BaseFragment
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedBooksDao
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedDatabase
import com.example.qataloog.model.responsemodel.dashboard.DashBoardList
import com.example.qataloog.network.DashBoardApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DashBoardListRepository @Inject constructor(
) : BaseFragment() {
//    @Inject
//    lateinit var recommendedDatabase: RecommendedDatabase

    lateinit var recommendedDao: RecommendedBooksDao

     fun getDashBoardListData(preferences: SharedPreferences): MutableLiveData<DashBoardList> {

        val mutableLiveData: MutableLiveData<DashBoardList> =
            MutableLiveData<DashBoardList>()
        val authToken: String? = preferences.getString("authToken", null)
        val dashboardApi: DashBoardApi? =
            UsersRetrofitClientInstance.getRetrofitInstance()
                ?.create(DashBoardApi::class.java)
        val call: Call<DashBoardList>? =
            dashboardApi?.getDashBoardListData("Bearer $authToken")
        call?.enqueue(object : Callback<DashBoardList> {
            override fun onResponse(
                call: Call<DashBoardList>,
                response: Response<DashBoardList>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                    runBlocking { response.body()?.let { saveNetworkRequest(it) } }
                }

            }

            override fun onFailure(call: Call<DashBoardList>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return mutableLiveData
    }

      suspend fun saveNetworkRequest(dashBoard: DashBoardList) {
          getDashBoardListFromDatabase(dashBoard)
    }

    private suspend fun getDashBoardListFromDatabase(dashBoard: DashBoardList) {
        recommendedDao = RecommendedDatabase.getInstance(Application()).recommendedBooksDao
        recommendedDao?.deleteRecommendedBooks()
        dashBoard?.data?.recommended?.let { recommendedDao.insertRecommendedBooks(it) }
    }
}