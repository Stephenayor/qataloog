package com.example.qataloog.model.responsemodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class LibrariesData (

    @SerializedName("id"                 ) var id               : Int?                        = null,
    @SerializedName("user_id"            ) var userId           : Int?                        = null,
    @SerializedName("name"               ) var name             : String?                     = null,
    @SerializedName("description"        ) var description      : String?                     = null,
    @SerializedName("deleted_at"         ) var deletedAt        : String?                     = null,
    @SerializedName("created_at"         ) var createdAt        : String?                     = null,
    @SerializedName("updated_at"         ) var updatedAt        : String?                     = null,
    @SerializedName("icon"               ) var icon             : String?                     = null,
    @SerializedName("books"              ) var books            : ArrayList<LibraryBooks>            = arrayListOf(),
    @SerializedName("users"              ) var users            : ArrayList<LibraryUser>           = arrayListOf(),
    @SerializedName("invites"            ) var invites          : ArrayList<Any>           = arrayListOf(),
    @SerializedName("get_pending_invite" ) var getPendingInvite : ArrayList<GetPendingInvite> = arrayListOf()

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("books"),
        TODO("users"),
        TODO("invites"),
        TODO("getPendingInvite")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(userId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(deletedAt)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LibrariesData> {
        override fun createFromParcel(parcel: Parcel): LibrariesData {
            return LibrariesData(parcel)
        }

        override fun newArray(size: Int): Array<LibrariesData?> {
            return arrayOfNulls(size)
        }
    }
}