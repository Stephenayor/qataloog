package com.example.qataloog.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qataloog.database.MyLibraryDao

class LibraryViewModelFactory(private val libraryDao: MyLibraryDao,
    private val application: Application)
    : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
            return LibraryViewModel( libraryDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}