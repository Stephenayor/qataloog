package com.example.qataloog.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PdfRetrofitInstance {
    companion object {
        val BASE_URL = "https://stagingbackend.qataloog.com/api/v1/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}