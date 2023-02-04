package com.example.qataloog.model.responsemodel.uploads

import com.google.gson.annotations.SerializedName


data class BookUploadsData (

    @SerializedName("content_type"      ) var contentType      : String? = null,
    @SerializedName("author"            ) var author           : String? = null,
    @SerializedName("title"             ) var title            : String? = null,
    @SerializedName("description"       ) var description      : String? = null,
    @SerializedName("category_id"       ) var categoryId       : Int?    = null,
    @SerializedName("sub_category_id"   ) var subCategoryId    : Int?    = null,
    @SerializedName("publisher"         ) var publisher        : String? = null,
    @SerializedName("status"            ) var status           : Int?    = null,
    @SerializedName("visibility_status" ) var visibilityStatus : Int?    = null,
    @SerializedName("isFree"            ) var isFree           : String? = null,
    @SerializedName("cost"              ) var cost             : Int?    = null,
    @SerializedName("language"          ) var language         : String? = null,
    @SerializedName("pub_year"          ) var pubYear          : String? = null,
    @SerializedName("user_id"           ) var userId           : Int?    = null,
    @SerializedName("cover"             ) var cover            : String? = null,
    @SerializedName("download_link"     ) var downloadLink     : String? = null,
    @SerializedName("book_id"           ) var bookId           : String? = null,
    @SerializedName("updated_at"        ) var updatedAt        : String? = null,
    @SerializedName("created_at"        ) var createdAt        : String? = null,
    @SerializedName("id"                ) var id               : Int?    = null

)