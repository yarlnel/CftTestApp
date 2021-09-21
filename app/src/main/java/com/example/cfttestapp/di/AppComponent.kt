package com.example.cfttestapp.di


import com.example.cfttestapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * `AppComponent` - Главный DI Компонент
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}