package com.example.qataloog.network

import com.example.qataloog.model.responsemodel.dashboard.DashBoardList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface DashBoardApi {

    @GET("book-listing")

    fun getDashBoardListData(@Header("Authorization") accessToken: String):
            Call<DashBoardList>
}