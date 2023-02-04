package com.example.qataloog.model.requestmodel

import com.google.gson.annotations.SerializedName

data class LibraryRequest(
    @SerializedName("name"      ) var libraryTitle      : String,
    @SerializedName("description")  var libraryDescription: String,
    @SerializedName("library_icon")  var libraryIcon: String
)
