package com.example.qataloog.model.responsemodel.dashboard

import com.google.gson.annotations.SerializedName


data class DashBoardList (

    @SerializedName("data"    ) var data    : DashBoardListData?   = DashBoardListData(),
    @SerializedName("message" ) var message : String? = null

)