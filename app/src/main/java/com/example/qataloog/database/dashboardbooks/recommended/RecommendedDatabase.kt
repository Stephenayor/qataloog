package com.example.qataloog.database.dashboardbooks.recommended

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Recommended::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecommendedDatabase : RoomDatabase() {

    abstract val recommendedBooksDao: RecommendedBooksDao

    companion object {

        private var INSTANCE: RecommendedDatabase? = null


        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): RecommendedDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecommendedDatabase:: class.java,
                        "recommended_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}