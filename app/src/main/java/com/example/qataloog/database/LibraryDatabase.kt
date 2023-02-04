package com.example.qataloog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyLibrary::class], version = 1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {

    abstract val libraryDao: MyLibraryDao

    companion object {

        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(context: Context): LibraryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LibraryDatabase::class.java,
                        "library_database"
                    )

                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}