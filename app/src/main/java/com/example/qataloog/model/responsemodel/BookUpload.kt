package com.example.qataloog.model.responsemodel

import com.example.qataloog.model.responsemodel.uploads.BookUploadsData
import com.google.gson.annotations.SerializedName

data class BookUpload (

    @SerializedName("data"    ) var data    : BookUploadsData?   = BookUploadsData(),
    @SerializedName("message" ) var message : String? = null

)
