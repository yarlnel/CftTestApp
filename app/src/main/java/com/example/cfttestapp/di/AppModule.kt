package com.example.cfttestapp.di

import dagger.Module

/**
 * `AppModule` - Главный Модуль
 */
@Module(includes = [
    RetrofitModule::class,
    RxModule::class,
    ContextModule::class,
    DataBaseModule::class,
    GsonModule::class,
    UiModule::class,
])
class AppModule {
}