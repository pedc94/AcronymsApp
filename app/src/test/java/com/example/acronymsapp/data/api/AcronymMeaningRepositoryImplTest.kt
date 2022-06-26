package com.example.acronymsapp.data.api

import com.example.acronymsapp.data.api.implementations.AcronymMeaningRepositoryImpl
import com.example.acronymsapp.data.api.interfaces.IAcronymMeaningRepository
import com.example.acronymsapp.data.api.interfaces.retrofit.IRAcronymMeaningService
import com.example.acronymsapp.data.api.model.AcronymMeaningResponse
import com.example.acronymsapp.data.api.model.LfsResponse
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AcronymMeaningRepositoryImplTest {
    private val acronymMeaningApi: IRAcronymMeaningService = mock()
    private val acronymMeaningRepository: IAcronymMeaningRepository by lazy { AcronymMeaningRepositoryImpl(acronymMeaningApi) }

    @Test
    fun `given api uses to getAcronymMeaning() with short form correctly, then return response successful with long forms of the acronym`() =
        runBlocking {
            //Given
            val longFormsResponse = LfsResponse(ACRONYM_MEANING, 200, 1990)
            val acronymMeaningResponse = AcronymMeaningResponse(ACRONYM, listOf(longFormsResponse))
            //When
            whenever(acronymMeaningApi.getAcronymMeaning(ACRONYM)).thenReturn(listOf(acronymMeaningResponse))
            val acronymMeaningsResponse = acronymMeaningRepository.getAcronymMeaning(ACRONYM)
            //Then
            assertThat(acronymMeaningsResponse?.first()?.sf.contentEquals(ACRONYM)).isTrue()
            assertThat(acronymMeaningsResponse?.first()?.lfs?.first()?.lf.contentEquals(
                ACRONYM_MEANING)).isTrue()
        }

    @Test
    fun `given api uses to getAcronymMeaning() with short form correctly, then return response unsuccessful`() =
        runBlocking {
            //When
            whenever(acronymMeaningApi.getAcronymMeaning(ACRONYM)).thenReturn(emptyList())
            val acronymMeaningsResponse = acronymMeaningRepository.getAcronymMeaning(ACRONYM)
            //Then
            assertThat(acronymMeaningsResponse.isNullOrEmpty()).isTrue()
        }

    companion object {
        private const val ACRONYM = "btw"
        private const val ACRONYM_MEANING = "by the way"
    }
}