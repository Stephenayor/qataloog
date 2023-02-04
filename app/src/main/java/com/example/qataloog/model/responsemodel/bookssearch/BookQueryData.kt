package com.example.qataloog.model.responsemodel.bookssearch

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class BookQueryData (

    @SerializedName("id"                 ) var id                 : Int?             = null,
    @SerializedName("book_id"            ) var bookId             : String?          = null,
    @SerializedName("author"             ) var author             : String?          = null,
    @SerializedName("title"              ) var title              : String?          = null,
    @SerializedName("description"        ) var description        : String?          = null,
    @SerializedName("isn"                ) var isn                : String?          = null,
    @SerializedName("cover"              ) var cover              : String?          = null,
    @SerializedName("download_link"      ) var downloadLink       : String?          = null,
    @SerializedName("cost"               ) var cost               : Int?             = null,
    @SerializedName("category"           ) var category           : Category?          = null,
    @SerializedName("subcategory"        ) var subcategory        : String?          = null,
    @SerializedName("subcategorydetails" ) var subcategorydetails : String?          = null,
    @SerializedName("categorydetails"    ) var categorydetails    : BookCategoryDetails? = BookCategoryDetails(),
    @SerializedName("status"             ) var status             : Int?             = null,
    @SerializedName("days_left"          ) var daysLeft           : String?          = null,
    @SerializedName("book_status"        ) var bookStatus         : String?          = null,
    @SerializedName("isFree"             ) var isFree             : String?          = null,
    @SerializedName("pub_year"           ) var pubYear            : String?          = null,
    @SerializedName("publisher"          ) var publisher          : String?          = null,
    @SerializedName("ISBN"               ) var ISBN               : String?          = null,
    @SerializedName("readtime"           ) var readtime           : Int?             = null,
    @SerializedName("amountearned"       ) var amountearned       : Double?          = null,
    @SerializedName("language"           ) var language           : String?          = null,
    @SerializedName("datecreated"        ) var datecreated        : String?          = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("category"),
        parcel.readString(),
        parcel.readString(),
        TODO("categorydetails"),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(bookId)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(isn)
        parcel.writeString(cover)
        parcel.writeString(downloadLink)
        parcel.writeValue(cost)
        parcel.writeString(subcategory)
        parcel.writeString(subcategorydetails)
        parcel.writeValue(status)
        parcel.writeString(daysLeft)
        parcel.writeString(bookStatus)
        parcel.writeString(isFree)
        parcel.writeString(pubYear)
        parcel.writeString(publisher)
        parcel.writeString(ISBN)
        parcel.writeValue(readtime)
        parcel.writeValue(amountearned)
        parcel.writeString(language)
        parcel.writeString(datecreated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookQueryData> {
        override fun createFromParcel(parcel: Parcel): BookQueryData {
            return BookQueryData(parcel)
        }

        override fun newArray(size: Int): Array<BookQueryData?> {
            return arrayOfNulls(size)
        }
    }
}

enum class Category(val value: String) {
    HigherEducation("Higher Education"),
    Tvet("TVET");

    companion object {
        public fun fromValue(value: String): Category = when (value) {
            "Higher Education" -> HigherEducation
            "TVET"             -> Tvet
            else               -> throw IllegalArgumentException()
        }
    }
}