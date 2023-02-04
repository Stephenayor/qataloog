package com.example.qataloog.model.responsemodel.bookssearch

import com.example.qataloog.model.responsemodel.dashboard.DashBoardSubCategories
import com.google.gson.annotations.SerializedName


data class BookCategoryDetails (

    @SerializedName("id"             ) var id            : Int?                     = null,
    @SerializedName("user_id"        ) var userId        : String?                  = null,
    @SerializedName("title"          ) var title         : String?                  = null,
    @SerializedName("rate"           ) var rate          : Double?                  = null,
    @SerializedName("description"    ) var description   : String?                  = null,
    @SerializedName("icon"           ) var icon          : String?                  = null,
    @SerializedName("deleted_at"     ) var deletedAt     : String?                  = null,
    @SerializedName("created_at"     ) var createdAt     : String?                  = null,
    @SerializedName("updated_at"     ) var updatedAt     : String?                  = null,
    @SerializedName("sub_categories" ) var subCategories : ArrayList<DashBoardSubCategories> = arrayListOf()

)