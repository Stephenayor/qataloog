package com.example.qataloog.network

import com.example.qataloog.model.LoginRequestModel
import com.example.qataloog.model.LoginResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {
    @POST("login")
    fun createUserLoginRequest(@Body requestModel: LoginRequestModel)
            : Call<LoginResponseModel>
}