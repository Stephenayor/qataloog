package com.example.qataloog.database.dashboardbooks.recommended

import androidx.room.TypeConverter
import com.example.qataloog.model.responsemodel.Role
import com.example.qataloog.model.responsemodel.dashboard.DashBoardSubCategories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class Converters {

    @TypeConverter
    fun stringToDashBoardSubCategories(data: String?): ArrayList<DashBoardSubCategories?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.EMPTY_LIST as ArrayList<DashBoardSubCategories?>?
        }
        val listType = object : TypeToken<ArrayList<DashBoardSubCategories?>?>() {}.type
        return gson.fromJson<ArrayList<DashBoardSubCategories?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: ArrayList<DashBoardSubCategories?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun stringToRole(data: String?): List<Role?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Role?>?>() {}.type
        return gson.fromJson<List<Role?>>(data, listType)
    }

    @TypeConverter
    fun roleObjectsToString(myObjects: List<Role?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun stringToList(data: String?): List<String?>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson<List<String?>>(data, listType)
    }

    @TypeConverter
    fun returnString(myObjects: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }


    //    @TypeConverter
//    fun fromString(value: String?): ArrayList<String?>? {
//        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
//        return Gson().fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromArrayList(list: ArrayList<String?>?): String? {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
}