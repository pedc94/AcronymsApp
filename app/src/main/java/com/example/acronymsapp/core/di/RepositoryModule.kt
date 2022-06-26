package com.example.acronymsapp.core.di

import com.example.acronymsapp.data.api.implementations.AcronymMeaningRepositoryImpl
import com.example.acronymsapp.data.api.interfaces.IAcronymMeaningRepository
import com.example.acronymsapp.data.api.interfaces.retrofit.IRAcronymMeaningService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(acronymMeaningApi: IRAcronymMeaningService): IAcronymMeaningRepository =
        AcronymMeaningRepositoryImpl(acronymMeaningApi)
}