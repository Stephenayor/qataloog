package com.example.qataloog.model.responsemodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Library (

    @SerializedName("data"    ) var data    : LibraryData?   = LibraryData(),
    @SerializedName("message" ) var message : String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("data"),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Library> {
        override fun createFromParcel(parcel: Parcel): Library {
            return Library(parcel)
        }

        override fun newArray(size: Int): Array<Library?> {
            return arrayOfNulls(size)
        }
    }
}
