package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName


data class Pivot (

    @SerializedName("library_id" ) var libraryId : Int?    = null,
    @SerializedName("book_id"    ) var bookId    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)