package com.example.qataloog.model.responsemodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class LibraryData (

    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("user_id"     ) var userId      : Int?    = null,
    @SerializedName("icon"        ) var icon        : String? = null,
    @SerializedName("updated_at"  ) var updatedAt   : String? = null,
    @SerializedName("created_at"  ) var createdAt   : String? = null,
    @SerializedName("id"          ) var id          : Int?    = null

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeValue(userId)
        parcel.writeString(icon)
        parcel.writeString(updatedAt)
        parcel.writeString(createdAt)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LibraryData> {
        override fun createFromParcel(parcel: Parcel): LibraryData {
            return LibraryData(parcel)
        }

        override fun newArray(size: Int): Array<LibraryData?> {
            return arrayOfNulls(size)
        }
    }
}
