package com.example.qataloog.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.qataloog.model.LoginRequestModel
import com.example.qataloog.model.LoginResponseModel
import com.example.qataloog.network.UserLoginApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserLoginRepository @Inject constructor() {

    fun createLoginRequest(
        userEmail: String,
        userPassword: String
    ): MutableLiveData<LoginResponseModel> {
        val mutableLiveData: MutableLiveData<LoginResponseModel> =
            MutableLiveData<LoginResponseModel>()
        val userLoginApi: UserLoginApi = UsersRetrofitClientInstance.getRetrofitInstance()
            ?.create(UserLoginApi::class.java)!!
        val call: Call<LoginResponseModel> = userLoginApi
            .createUserLoginRequest(LoginRequestModel(userEmail, userPassword))
        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(
                call: Call<LoginResponseModel>,
                response: Response<LoginResponseModel>
            ) {
                Log.d("Post Response", response.body().toString())
                mutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return mutableLiveData
    }
}