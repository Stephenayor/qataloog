package com.example.qataloog.database.dashboardbooks.recommended

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qataloog.model.responsemodel.dashboard.Recommended

@Dao
interface RecommendedBooksDao {

    @Query("SELECT * FROM recommended")
    fun getAllRecommendedBooks(): LiveData<List<Recommended>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendedBooks(recommended: List<Recommended>)

    @Query("DELETE FROM recommended")
     fun deleteRecommendedBooks()

}