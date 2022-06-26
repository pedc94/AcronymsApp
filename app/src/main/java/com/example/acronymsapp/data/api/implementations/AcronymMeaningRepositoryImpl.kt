package com.example.acronymsapp.data.api.implementations

import com.example.acronymsapp.data.api.interfaces.IAcronymMeaningRepository
import com.example.acronymsapp.data.api.interfaces.retrofit.IRAcronymMeaningService
import com.example.acronymsapp.data.api.model.AcronymMeaningResponse

class AcronymMeaningRepositoryImpl(
    private val acronymMeaningService: IRAcronymMeaningService
): IAcronymMeaningRepository {
    override suspend fun getAcronymMeaning(sf: String): List<AcronymMeaningResponse>? {
        return acronymMeaningService.getAcronymMeaning(sf)
    }
}