package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName


data class GetPendingInvite (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("library_id"   ) var libraryId   : Int?    = null,
    @SerializedName("user_id"      ) var userId      : String? = null,
    @SerializedName("email"        ) var email       : String? = null,
    @SerializedName("accept_token" ) var acceptToken : String? = null,
    @SerializedName("deny_token"   ) var denyToken   : String? = null,
    @SerializedName("created_at"   ) var createdAt   : String? = null,
    @SerializedName("updated_at"   ) var updatedAt   : String? = null,
    @SerializedName("status"       ) var status      : String? = null

)