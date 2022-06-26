package com.example.acronymsapp.data.api.interfaces.retrofit

import com.example.acronymsapp.data.api.model.AcronymMeaningResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IRAcronymMeaningService {
    @GET("dictionary.py")
    suspend fun getAcronymMeaning(
        @Query("sf") sf: String
    ): List<AcronymMeaningResponse>?
}