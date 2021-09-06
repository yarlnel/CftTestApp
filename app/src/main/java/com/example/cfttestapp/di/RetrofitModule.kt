package com.example.cfttestapp.di

import com.example.cfttestapp.api.CentralBankService
import com.google.gson.internal.bind.CollectionTypeAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideConvectorFactory () : Converter.Factory
        = GsonConverterFactory.create()

    @Provides @Singleton
    fun provideCallAdapterFactory () : CallAdapter.Factory
            = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ) : Retrofit
        = Retrofit
            .Builder()
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .build()

    @Provides
    fun provideCentralBankService(retrofit: Retrofit) : CentralBankService
        = retrofit.create(CentralBankService::class.java)

}