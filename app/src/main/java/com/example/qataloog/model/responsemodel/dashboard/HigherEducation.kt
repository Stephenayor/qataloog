package com.example.qataloog.model.responsemodel.dashboard

import com.example.qataloog.model.responsemodel.LibraryUser
import com.google.gson.annotations.SerializedName


data class HigherEducation (

@SerializedName("id"                ) var id               : Int?      = null,
@SerializedName("book_id"           ) var bookId           : String?   = null,
@SerializedName("author"            ) var author           : String?   = null,
@SerializedName("title"             ) var title            : String?   = null,
@SerializedName("description"       ) var description      : String?   = null,
@SerializedName("isn"               ) var isn              : String?   = null,
@SerializedName("ISBN"              ) var ISBN             : String?   = null,
@SerializedName("cover"             ) var cover            : String?   = null,
@SerializedName("download_link"     ) var downloadLink     : String?   = null,
@SerializedName("cost"              ) var cost             : Int?      = null,
@SerializedName("category_id"       ) var categoryId       : Int?      = null,
@SerializedName("sub_category_id"   ) var subCategoryId    : String?   = null,
@SerializedName("user_id"           ) var userId           : Int?      = null,
@SerializedName("status"            ) var status           : Int?      = null,
@SerializedName("deleted_at"        ) var deletedAt        : String?   = null,
@SerializedName("created_at"        ) var createdAt        : String?   = null,
@SerializedName("updated_at"        ) var updatedAt        : String?   = null,
@SerializedName("visibility_status" ) var visibilityStatus : Int?      = null,
@SerializedName("delete_date"       ) var deleteDate       : String?   = null,
@SerializedName("language"          ) var language         : String?   = null,
@SerializedName("isFree"            ) var isFree           : String?   = null,
@SerializedName("pub_year"          ) var pubYear          : String?   = null,
@SerializedName("newpublisher"      ) var newpublisher     : String?   = null,
@SerializedName("publisher"         ) var publisher        : String?   = null,
@SerializedName("readtime"          ) var readtime         : Int?      = null,
@SerializedName("amountearned"      ) var amountearned     : Int?      = null,
@SerializedName("category"          ) var category         : DashBoardCategory? = DashBoardCategory(),
@SerializedName("user"              ) var user             : LibraryUser?     = LibraryUser()

)