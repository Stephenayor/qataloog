package com.example.qataloog.network

import com.example.qataloog.model.Token
import com.example.qataloog.model.responsemodel.RecommendedBooks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RecommendedBooksApi {

    @GET("book")
    fun getRecommendedBooks(@Header("Authorization") accessToken: String):
            Call<RecommendedBooks>

}