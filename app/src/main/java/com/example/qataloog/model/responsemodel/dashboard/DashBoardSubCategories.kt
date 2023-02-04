package com.example.qataloog.model.responsemodel.dashboard

import com.google.gson.annotations.SerializedName


data class DashBoardSubCategories (

    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("name"        ) var name       : String? = null,
    @SerializedName("french_name" ) var frenchName : FrenchName? = null,
    @SerializedName("category_id" ) var categoryId : Int?    = null,
    @SerializedName("created_at"  ) var createdAt  : String? = null,
    @SerializedName("updated_at"  ) var updatedAt  : String? = null

)
enum class FrenchName(val value: String) {
    Comptabilité("Comptabilité"),
    Update1("update1");

    companion object {
        public fun fromValue(value: String): FrenchName = when (value) {
            "Comptabilité" -> Comptabilité
            "update1"      -> Update1
            else           -> throw IllegalArgumentException()
        }
    }
}