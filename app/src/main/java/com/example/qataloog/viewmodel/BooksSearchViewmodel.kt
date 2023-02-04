package com.example.qataloog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.qataloog.model.responsemodel.bookssearch.BooksQueryResult
import com.example.qataloog.repository.BooksSearchRepository
import javax.inject.Inject

class BooksSearchViewmodel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var booksSearchRepository: BooksSearchRepository

    fun getBooksSearchResults(searchTitle: String): LiveData<BooksQueryResult>{
       return booksSearchRepository.getBooksSearchResults(searchTitle)
    }

    fun getSlowNetworkMessageError(): LiveData<Boolean>{
        return booksSearchRepository.getSlowNetworkMessageError()
    }

    fun getBooksNotFoundError(): LiveData<Boolean> {
        return booksSearchRepository.getBooksNotFoundError()
    }

}