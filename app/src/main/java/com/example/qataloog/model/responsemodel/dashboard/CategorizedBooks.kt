package com.example.qataloog.model.responsemodel.dashboard

import com.google.gson.annotations.SerializedName


data class CategorizedBooks (

    @SerializedName("TVET"  )          var TVET :      ArrayList<TVET> = arrayListOf(),
    @SerializedName("Higher Education" )   var  HigherEducation       : ArrayList<HigherEducation> = arrayListOf()

)