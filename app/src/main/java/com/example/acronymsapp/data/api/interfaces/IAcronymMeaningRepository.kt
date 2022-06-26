package com.example.acronymsapp.data.api.interfaces

import com.example.acronymsapp.data.api.model.AcronymMeaningResponse

interface IAcronymMeaningRepository {
    suspend fun getAcronymMeaning(sf: String): List<AcronymMeaningResponse>?
}