package com.example.qataloog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyLibraryDao {

    @Query("SELECT * FROM my_library_table ORDER BY title DESC")
    fun getAllExistingLibrary(): LiveData<List<MyLibrary>>
//
//    @Query("SELECT * FROM notes_list_table WHERE id = :id")
//    fun getNoteById(id: Int): Notes

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createLibrary(library: MyLibrary)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateNote(note: Notes)
//
//    @Delete
//    suspend fun deleteNote(note: Notes)
}