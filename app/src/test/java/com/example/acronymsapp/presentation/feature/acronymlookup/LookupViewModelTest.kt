package com.example.acronymsapp.presentation.feature.acronymlookup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.acronymsapp.core.util.ResultStatus
import com.example.acronymsapp.data.api.interfaces.IAcronymMeaningRepository
import com.example.acronymsapp.data.api.model.AcronymMeaningResponse
import com.example.acronymsapp.data.api.model.LfsResponse
import com.google.common.truth.Truth.assertThat
import com.example.acronymsapp.utils.getOrAwaitValueTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class LookupViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel: LookUpViewModel
    private val repository: IAcronymMeaningRepository = mock()

    init {
        viewModel = LookUpViewModel(repository)
    }

    @Test
    fun `given text not null, execute call to repository and error is null`() {
        //Given
        val text = ACRONYM
        val longFormsResponse = LfsResponse(ACRONYM_MEANING, 200, 1990)
        val acronymMeaningResponse = AcronymMeaningResponse(ACRONYM, listOf(longFormsResponse))
        //When
        runBlocking {
            whenever(repository.getAcronymMeaning(any())).thenReturn(listOf(acronymMeaningResponse))
        }
        viewModel.onLookUpClick(text)
        //Then
        val dataLoading = viewModel.data.getOrAwaitValueTest()
        assertThat(dataLoading).isInstanceOf(ResultStatus.Loading::class.java)

        val dataSuccess = viewModel.data.getOrAwaitValueTest()
        assertThat(dataSuccess).isInstanceOf(ResultStatus.Success::class.java)

        val errorEmpty = viewModel.errorEmpty.getOrAwaitValueTest()
        assertThat(errorEmpty).isNull()
    }

    @Test
    fun `given text blank, error is not null`() {
        //Given
        val text = ""
        //When
        viewModel.onLookUpClick(text)
        //Then
        val errorEmpty = viewModel.errorEmpty.getOrAwaitValueTest()
        assertThat(errorEmpty == 2131689510).isTrue()
    }

    companion object {
        private const val ACRONYM = "IT"
        private const val ACRONYM_MEANING = "Information Technology"
    }
}