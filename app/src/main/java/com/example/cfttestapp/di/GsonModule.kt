package com.example.cfttestapp.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GsonModule {
    @Provides @Singleton
    fun provideGson () : Gson
        = Gson()
}