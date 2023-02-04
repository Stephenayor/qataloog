package com.example.qataloog.network

import com.example.qataloog.model.responsemodel.Library
import com.example.qataloog.model.responsemodel.LibraryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface MyLibraryApi {

    @Multipart
    @POST("library")
    fun createLibraryRequest(
        @Header("Authorization") accessToken: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part libraryIcon: MultipartBody.Part
    ): Call<Library>

    @GET("my-libraries")
    fun getAllCreatedLibrary(@Header("Authorization") accessToken: String):
            Call<LibraryResponse>
}
