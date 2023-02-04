package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName


data class LibraryResponse (

    @SerializedName("data"    ) var data    : ArrayList<LibrariesData> = arrayListOf(),
    @SerializedName("message" ) var message : String?         = null

)
