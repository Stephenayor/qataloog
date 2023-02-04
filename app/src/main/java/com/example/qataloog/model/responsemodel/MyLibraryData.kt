package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName

data class MyLibraryData (

    @SerializedName("id"                 ) var id               : Int?                        = null,
    @SerializedName("user_id"            ) var userId           : Int?                        = null,
    @SerializedName("name"               ) var name             : String?                     = null,
    @SerializedName("description"        ) var description      : String?                     = null,
    @SerializedName("deleted_at"         ) var deletedAt        : String?                     = null,
    @SerializedName("created_at"         ) var createdAt        : String?                     = null,
    @SerializedName("updated_at"         ) var updatedAt        : String?                     = null,
    @SerializedName("icon"               ) var icon             : String?                     = null,
    @SerializedName("books"              ) var books            : ArrayList<LibraryBooks>            = arrayListOf(),
    @SerializedName("users"              ) var users            : ArrayList<String>           = arrayListOf(),
    @SerializedName("invites"            ) var invites          : ArrayList<String>           = arrayListOf(),
    @SerializedName("get_pending_invite" ) var getPendingInvite : ArrayList<GetPendingInvite> = arrayListOf()

)