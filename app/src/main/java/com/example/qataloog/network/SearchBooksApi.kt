package com.example.qataloog.network

import com.example.qataloog.model.responsemodel.bookssearch.BooksQueryResult
import com.example.qataloog.model.responsemodel.dashboard.DashBoardList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchBooksApi {

    @GET("book/search")
    fun getBooks(
        @Query(value = "q") searchTitle: String?
    ): Call<BooksQueryResult>
}