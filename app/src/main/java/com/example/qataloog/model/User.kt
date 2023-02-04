package com.example.qataloog.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id"                  ) var id                 : Int?              = null,
    @SerializedName("uuid"                ) var uuid               : String?           = null,
    @SerializedName("name"                ) var name               : String?           = null,
    @SerializedName("email"               ) var email              : String?           = null,
    @SerializedName("occupation"          ) var occupation         : String?           = null,
    @SerializedName("interests"           ) var interests          : ArrayList<String> = arrayListOf(),
    @SerializedName("profile_pic"         ) var profilePic         : String?           = null,
    @SerializedName("subscription_status" ) var subscriptionStatus : Boolean?          = null,
    @SerializedName("has_used_trial"      ) var hasUsedTrial       : Boolean?          = null,
    @SerializedName("on_trial"            ) var onTrial            : Boolean?          = null,
    @SerializedName("roles"               ) var roles              : ArrayList<String> = arrayListOf()
)
