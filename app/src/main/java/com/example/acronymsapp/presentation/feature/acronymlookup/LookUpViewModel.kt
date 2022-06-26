package com.example.acronymsapp.presentation.feature.acronymlookup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronymsapp.R
import com.example.acronymsapp.core.util.ResultStatus
import com.example.acronymsapp.data.api.interfaces.IAcronymMeaningRepository
import com.example.acronymsapp.domain.mappers.toDomain
import com.example.acronymsapp.domain.model.AcronymsMeaningModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LookUpViewModel @Inject constructor(private val acronymMeaningRepository: IAcronymMeaningRepository) : ViewModel() {
    private val _data = MutableLiveData<ResultStatus<AcronymsMeaningModel?>>()
    val data: LiveData<ResultStatus<AcronymsMeaningModel?>>
    get() = _data

    private val _errorEmpty = MutableLiveData<Int?>()
    val errorEmpty: LiveData<Int?>
    get() = _errorEmpty

    private fun fetchService(sf: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.postValue(ResultStatus.Loading())
            try {
                val meaningList = acronymMeaningRepository.getAcronymMeaning(sf)

                if (!meaningList.isNullOrEmpty()) {
                    var model: AcronymsMeaningModel? = null
                    meaningList.forEach { meanings ->
                        model = meanings.toDomain(sf)
                    }
                    _data.postValue(ResultStatus.Success(model))
                } else
                    _data.postValue(ResultStatus.Error(ERROR_NOT_FOUND, null))
            } catch (e: Exception) {
                _data.postValue(ResultStatus.Error(ERROR_GENERIC, null))
            }
        }
    }

    fun onLookUpClick(text: String) {
        if (text.isBlank().not()) {
            fetchService(text.trim())
            _errorEmpty.postValue(null)
        } else {
            _errorEmpty.postValue(R.string.error_empty)
        }
    }

    companion object {
        private const val ERROR_NOT_FOUND = "Acronym meaning not found"
        private const val ERROR_GENERIC = "An error occurred while loading data"
    }
}