package com.example.qataloog.model.responsemodel

import com.google.gson.annotations.SerializedName


data class SubCategories (

    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("name"        ) var name       : String? = null,
    @SerializedName("category_id" ) var categoryId : Int?    = null,
    @SerializedName("created_at"  ) var createdAt  : String? = null,
    @SerializedName("updated_at"  ) var updatedAt  : String? = null

)