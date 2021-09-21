package com.example.cfttestapp.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * `UiModule` - Модуль отвечающий за Ui
 */
@Module
class UiModule {
    @Provides
    fun provideLinearLayoutManager (appContext: Context) : LinearLayoutManager
        = LinearLayoutManager(appContext)
}