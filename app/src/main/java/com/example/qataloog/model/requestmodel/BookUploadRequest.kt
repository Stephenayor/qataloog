package com.example.qataloog.model.requestmodel

import com.google.gson.annotations.SerializedName


data class BookUploadRequest (

    @SerializedName("content_type"      ) var contentType      : String? = null,
    @SerializedName("author"            ) var author           : String? = null,
    @SerializedName("title"             ) var title            : String? = null,
    @SerializedName("description"       ) var description      : String? = null,
    @SerializedName("category_id"       ) var categoryId       : Int?    = null,
    @SerializedName("sub_category_id"   ) var subCategoryId    : Int?    = null,
    @SerializedName("isbn"              ) var isbn             : String? = null,
    @SerializedName("publisher"         ) var publisher        : String? = null,
    @SerializedName("status"            ) var status           : Int?    = null,
    @SerializedName("visibility_status" ) var visibilityStatus : Int?    = null,
    @SerializedName("isFree"            ) var isFree           : String? = null,
    @SerializedName("cost"              ) var cost             : Int?    = null,
    @SerializedName("language"          ) var language         : String? = null,
    @SerializedName("pub_year"          ) var pubYear          : String? = null

)