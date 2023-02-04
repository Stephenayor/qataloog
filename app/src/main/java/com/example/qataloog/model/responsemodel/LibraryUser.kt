package com.example.qataloog.model.responsemodel

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.qataloog.database.dashboardbooks.recommended.Converters
import com.example.qataloog.database.dashboardbooks.recommended.RoleConverter
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class LibraryUser (

    @SerializedName("id"                  ) var id                 : Int?              = null,
    @SerializedName("uuid"                ) var uuid               : String?           = null,
    @SerializedName("name"                ) var name               : String?           = null,
    @SerializedName("email"               ) var email              : String?           = null,
    @SerializedName("occupation"          ) var occupation         : String?           = null,
    @SerializedName("interests"           ) var interests          : List<String> = listOf(),
    @SerializedName("profile_pic"         ) var profilePic         : String?           = null,
    @SerializedName("subscription_status" ) var subscriptionStatus : Boolean?          = null,
    @SerializedName("has_used_trial"      ) var hasUsedTrial       : Boolean?          = null,
    @SerializedName("on_trial"            ) var onTrial            : Boolean?          = null,
    @SerializedName("roles"               ) var roles              : List<Role> = arrayListOf()
)
enum class Role(val value: String) {
    Administrator("Administrator"),
    User("User");

    companion object {
        public fun fromValue(value: String): Role = when (value) {
            "Administrator" -> Administrator
            "User"          -> User
            else            -> throw IllegalArgumentException()
        }
    }
}