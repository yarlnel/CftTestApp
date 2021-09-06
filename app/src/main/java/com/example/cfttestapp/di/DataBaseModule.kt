package com.example.cfttestapp.di

import android.content.Context
import androidx.room.Room
import com.example.cfttestapp.db.MainDao
import com.example.cfttestapp.db.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides @Singleton
    fun provideMainDatabase (appContext: Context) : MainDatabase
        = Room
            .databaseBuilder(
                appContext,
                MainDatabase::class.java,
                "main_db"
            )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides @Singleton
    fun provideMainDao (mainDatabase: MainDatabase) : MainDao
        = mainDatabase . mainDao()
}