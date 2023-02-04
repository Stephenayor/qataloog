package com.example.qataloog.model.responsemodel.dashboard

import com.google.gson.annotations.SerializedName


data class DashBoardListData (

    @SerializedName("categorized_books" ) var categorizedBooks : CategorizedBooks?       = CategorizedBooks(),
    @SerializedName("top_read"          ) var topRead          : ArrayList<TopRead>      = arrayListOf(),
    @SerializedName("recommended"       ) var recommended      : ArrayList<Recommended>  = arrayListOf(),
    @SerializedName("category"          ) var category         : ArrayList<DashBoardCategory>     = arrayListOf(),
    @SerializedName("userInterest"      ) var userInterest     : ArrayList<UserInterest> = arrayListOf()

)