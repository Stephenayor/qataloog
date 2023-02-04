package com.example.qataloog.model.responsemodel.bookssearch

import com.google.gson.annotations.SerializedName


data class BooksQueryResult (

    @SerializedName("data"    ) var data    : ArrayList<BookQueryData> = arrayListOf(),
    @SerializedName("message" ) var message : String?         = null

)