package com.example.qataloog.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_library_table")
data class MyLibrary(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name="image")
    var image: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        id?.let { parcel.writeInt(it) }
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyLibrary> {
        override fun createFromParcel(parcel: Parcel): MyLibrary {
            return MyLibrary(parcel)
        }

        override fun newArray(size: Int): Array<MyLibrary?> {
            return arrayOfNulls(size)
        }
    }
}
