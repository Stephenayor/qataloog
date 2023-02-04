package com.example.qataloog.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.qataloog.database.LibraryDatabase
import com.example.qataloog.database.MyLibrary
import com.example.qataloog.database.MyLibraryDao
import com.example.qataloog.model.responsemodel.LibraryResponse
import com.example.qataloog.repository.LibraryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class LibraryViewModel(
    val libraryDao: MyLibraryDao,
    application: Application
) : AndroidViewModel(application) {

    val createdLibraries: LiveData<List<MyLibrary>>
    val libraryRepository: LibraryRepository

    init {
        val libraryDao = LibraryDatabase.getInstance(application).libraryDao
        libraryRepository = LibraryRepository(libraryDao)
        createdLibraries = libraryRepository.existingLibraryList
    }

    fun createLibrary(preferences: SharedPreferences, libraryTitle: String, libraryDescription: String,
                      libraryImage: File) = viewModelScope.launch(Dispatchers.IO) {
            libraryRepository.createLibraryRequest(preferences, libraryTitle, libraryDescription, libraryImage)
        }

    fun getResponseMessage(): LiveData<String> {
        return libraryRepository.getResponseMessage()
    }

    fun getAllExistingLibraries(prefences: SharedPreferences): LiveData<LibraryResponse> {
        return libraryRepository.getAllLibrary(prefences)
    }
}