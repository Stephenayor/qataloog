package com.example.qataloog.network

import com.example.qataloog.model.requestmodel.BookUploadRequest
import com.example.qataloog.model.responsemodel.BookUpload
import com.example.qataloog.model.responsemodel.Library
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UploadsApi {

    @Multipart
    @POST("book")
    fun uploadBooks(
        @Header("Authorization") accessToken: String,
        @Part("content_type") content_type: RequestBody,
        @Part("author") author: RequestBody,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("category_id") category_id: RequestBody,
        @Part("sub_category_id") sub_category_id: RequestBody,
        @Part("isbn") isbn: RequestBody,
        @Part("publisher") publisher: RequestBody,
        @Part("status") status: RequestBody,
        @Part("visibility_status") visibility_status: RequestBody,
        @Part("isFree") isFree: RequestBody,
        @Part("cost") cost: RequestBody,
        @Part("language") language: RequestBody,
        @Part("pub_year") pub_year: RequestBody,
        @Part book: MultipartBody.Part
    ): Call<BookUpload>
}