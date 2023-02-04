package com.example.qataloog.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedBooksDao
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(app: Application) : RecommendedDatabase {
        return RecommendedDatabase.getInstance(provideAppContext())
}
    @Singleton
    @Provides
    fun provideAppContext() : Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun getRecommendedBooksDao(recommendedDatabase: RecommendedDatabase): RecommendedBooksDao{
        return recommendedDatabase.recommendedBooksDao
    }

}