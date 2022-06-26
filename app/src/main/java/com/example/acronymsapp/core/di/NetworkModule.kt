package com.example.acronymsapp.core.di

import com.example.acronymsapp.BuildConfig
import com.example.acronymsapp.data.api.interfaces.retrofit.IRAcronymMeaningService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.API_BASE_URL)
        client(okHttpClient)
        addConverterFactory(MoshiConverterFactory.create())
    }.build()

    @Provides
    @Singleton
    fun providesAcronymMeaningApi(retrofit: Retrofit): IRAcronymMeaningService =
        retrofit.create(IRAcronymMeaningService::class.java)
}