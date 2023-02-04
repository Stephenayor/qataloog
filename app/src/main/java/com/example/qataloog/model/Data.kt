package com.example.qataloog.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("user"   ) var user   : User?  = User(),
    @SerializedName("token"  ) var token  : Token? = Token(),
    @SerializedName("status" ) var status : Int?   = null
)
