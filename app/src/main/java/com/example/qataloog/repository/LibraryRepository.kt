package com.example.qataloog.repository

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.qataloog.database.MyLibrary
import com.example.qataloog.database.MyLibraryDao
import com.example.qataloog.model.responsemodel.Library
import com.example.qataloog.model.responsemodel.LibraryResponse
import com.example.qataloog.network.MyLibraryApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class LibraryRepository(private val libraryDao: MyLibraryDao) {

    val existingLibraryList: LiveData<List<MyLibrary>> = libraryDao.getAllExistingLibrary()
    var messageLiveData: MutableLiveData<String> =
        MutableLiveData<String>()

    fun createLibraryRequest(
        preferences: SharedPreferences,
        libraryTitle: String,
        libraryDescription: String,
        libraryIcon: File
    ): MutableLiveData<Library> {
        val mutableLiveData: MutableLiveData<Library> =
            MutableLiveData<Library>()

        val authToken: String? = preferences.getString("authToken", null)
        val libraryImageFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), libraryIcon)
        val libraryImage = MultipartBody.Part.createFormData("library_icon", libraryIcon.name, libraryImageFile)
        val libraryTitleData =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), libraryTitle)
        val libraryDescriptionData =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), libraryDescription)

        val libraryApi: MyLibraryApi = UsersRetrofitClientInstance.getRetrofitInstance()
            ?.create(MyLibraryApi::class.java)!!
        val call: Call<Library> = libraryApi
            .createLibraryRequest("Bearer $authToken", libraryTitleData, libraryDescriptionData,
                libraryImage)
        call.enqueue(object : Callback<Library> {
            override fun onResponse(
                call: Call<Library>,
                response: Response<Library>
            ) {
                if (response.isSuccessful) {
                    Log.d("LIBRARY CREATED!!", response.body().toString())
                    messageLiveData.value = response.message()
                }
                Log.d("Post Response", response.body().toString())
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Library>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return mutableLiveData
    }

    fun getResponseMessage(): LiveData<String> {
        return messageLiveData
    }

    fun getAllLibrary(preferences: SharedPreferences): MutableLiveData<LibraryResponse> {

        val mutableLiveData: MutableLiveData<LibraryResponse> =
            MutableLiveData<LibraryResponse>()
        val authToken: String? = preferences.getString("authToken", null)
        val MyLibraryApi: MyLibraryApi? =
            UsersRetrofitClientInstance.getRetrofitInstance()
                ?.create(MyLibraryApi::class.java)
        val call: Call<LibraryResponse>? =
            MyLibraryApi?.getAllCreatedLibrary("Bearer $authToken")
        call?.enqueue(object : Callback<LibraryResponse> {
            override fun onResponse(
                call: Call<LibraryResponse>,
                response: Response<LibraryResponse>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }

            }

            override fun onFailure(call: Call<LibraryResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return mutableLiveData
    }
}
