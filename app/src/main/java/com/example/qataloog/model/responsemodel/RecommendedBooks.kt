package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName

data class RecommendedBooks(
    @SerializedName("data"    ) var data    : ArrayList<BookData> = arrayListOf(),
    @SerializedName("message" ) var message : String?         = null
)
