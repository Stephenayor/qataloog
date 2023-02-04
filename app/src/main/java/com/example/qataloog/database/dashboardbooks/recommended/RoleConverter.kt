package com.example.qataloog.database.dashboardbooks.recommended

import androidx.room.TypeConverter
import com.example.qataloog.model.responsemodel.Role
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class RoleConverter {

    @TypeConverter
    fun stringToRole(data: String?): List<Role> {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Role>>() {}.type
        return gson.fromJson<List<Role>>(data, listType)
    }

    @TypeConverter
    fun roleObjectsToString(myObjects: List<Role>): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun toRole(value: String) = enumValueOf<Role>(value)

    @TypeConverter
    fun fromRole(value: Role) = value.name
}