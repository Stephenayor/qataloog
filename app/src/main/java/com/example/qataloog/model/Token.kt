package com.example.qataloog.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token" ) var accessToken : String? = null,
    @SerializedName("token_type"   ) var tokenType   : String? = null,
    @SerializedName("expires_in"   ) var expiresIn   : Float?    = null
)
