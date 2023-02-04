package com.example.qataloog.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class UsersRetrofitClientInstance {
    companion object retrofitInstance {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://stagingbackend.qataloog.com/api/v1/"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        private fun provideInterceptor(): OkHttpClient.Builder {
            val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
            val interceptor = Interceptor { chain ->
                val url = chain.request().url.newBuilder().build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                val response = chain.proceed(request)
                response
            }

            val okHTTP: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)

                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(5, TimeUnit.SECONDS)
//                .writeTimeout(5, TimeUnit.SECONDS)

            return okHTTP
        }


        fun getRetrofitInstance(): Retrofit? {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(provideInterceptor().build())
                .build()

            return retrofit
        }
    }
}