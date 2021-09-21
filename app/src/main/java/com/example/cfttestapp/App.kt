package com.example.cfttestapp

import android.app.Application
import android.content.Context
import com.example.cfttestapp.di.AppComponent
import com.example.cfttestapp.di.ContextModule
import com.example.cfttestapp.di.DaggerAppComponent
import com.example.cfttestapp.di.DataBaseModule

/**
 * `App.kt` - главный класс Application нашего приложения
 */
class App : Application () {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}

val Context.appComponent : AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this . applicationContext . appComponent
    }


