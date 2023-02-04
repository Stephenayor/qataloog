package com.example.qataloog.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel (
    @SerializedName("data"    ) var data    : Data?   = Data(),
    @SerializedName("message" ) var message : String? = null
)
