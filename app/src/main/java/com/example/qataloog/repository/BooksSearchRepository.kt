package com.example.qataloog.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.qataloog.model.responsemodel.bookssearch.BooksQueryResult
import com.example.qataloog.network.SearchBooksApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BooksSearchRepository @Inject constructor() {
    private var booksNotFound =  MutableLiveData<Boolean>()
    private var slowNetworkIssues =  MutableLiveData<Boolean>()


    fun getBooksSearchResults(searchTitle: String): MutableLiveData<BooksQueryResult> {
        val mutableLiveData: MutableLiveData<BooksQueryResult> =
            MutableLiveData<BooksQueryResult>()
        val booksApi: SearchBooksApi? =
            UsersRetrofitClientInstance.getRetrofitInstance()
                ?.create(SearchBooksApi::class.java)
        val call: Call<BooksQueryResult>? =
            booksApi?.getBooks(searchTitle)

        call?.enqueue(object : Callback<BooksQueryResult> {
            override fun onResponse(
                call: Call<BooksQueryResult>,
                response: Response<BooksQueryResult>
            ) {
                if (response.isSuccessful) {
                    mutableLiveData.value = response.body()
                }else{
                    booksNotFound.value = true
                }
            }

            override fun onFailure(call: Call<BooksQueryResult>, t: Throwable) {
                t.printStackTrace()

                if (t is UnknownHostException) {
                    Log.d("TIME OUT", "NO INTERNET")
                   slowNetworkIssues.value = true
                }

                if (t is SocketTimeoutException) {
                    slowNetworkIssues.value = true
                    Log.d("TIME OUT", "FAILED TO CONNECT")
                }
            }
        })
        return mutableLiveData
    }

    fun getSlowNetworkMessageError(): MutableLiveData<Boolean> {
        return slowNetworkIssues
    }

    fun getBooksNotFoundError(): LiveData<Boolean> {
        return booksNotFound
    }
}