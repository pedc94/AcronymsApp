package com.example.acronymsapp.mappers

import com.example.acronymsapp.data.api.model.AcronymMeaningResponse
import com.example.acronymsapp.data.api.model.LfsResponse
import com.example.acronymsapp.domain.mappers.toDomain
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AcronymMeaningExtTest {
    @Test
    fun `convert AcronymMeaningResponse to AcronymsMeaningModel, then verify fields match correctly`() {
        //Given
        val longFormsResponse = LfsResponse(ACRONYM_MEANING, 200, 1990)
        val acronymMeaningResponse = AcronymMeaningResponse(ACRONYM, listOf(longFormsResponse))
        //When
        val acronymCast = acronymMeaningResponse.toDomain(ACRONYM)
        //Then
        assertThat(acronymCast.lfs.first().lf.contentEquals(ACRONYM_MEANING)).isTrue()
    }

    companion object {
        private const val ACRONYM = "IT"
        private const val ACRONYM_MEANING = "Information Technology"
    }
}