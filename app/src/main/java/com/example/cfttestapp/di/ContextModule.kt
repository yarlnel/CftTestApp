package com.example.cfttestapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * `ContextModule` - Модуль отвечающий за предоставление контекста
 */
@Module
class ContextModule (private val appContext: Context) {
    @Provides @Singleton
    fun provideAppContext () : Context
        = appContext
}